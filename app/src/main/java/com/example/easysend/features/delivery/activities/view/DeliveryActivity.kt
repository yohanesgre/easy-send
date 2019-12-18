package com.example.easysend.features.delivery.activities.view

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.easysend.R
import com.example.easysend.databinding.ActivityDeliveryBinding
import com.example.easysend.extentions.dpToPx
import com.example.easysend.extentions.getColorCompat
import com.example.easysend.features.delivery.adapter.TimelineAdapter
import com.github.vipulasri.timelineview.TimelineView
import com.github.vipulasri.timelineview.sample.model.OrderStatus
import com.github.vipulasri.timelineview.sample.model.Orientation
import com.github.vipulasri.timelineview.sample.model.TimeLineModel
import com.github.vipulasri.timelineview.sample.model.TimelineAttributes
import com.google.android.material.snackbar.Snackbar
import com.mapbox.android.core.location.*
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.api.directions.v5.models.DirectionsResponse
import com.mapbox.api.directions.v5.models.DirectionsRoute
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.location.LocationComponent
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.lang.ref.WeakReference

class DeliveryActivity : AppCompatActivity(), PermissionsListener {

    private val listItemTimeline = ArrayList<TimeLineModel>()
    private lateinit var binding: ActivityDeliveryBinding

    private val DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L
    private val DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5

    private var mapView: MapView? = null
    lateinit var mapboxMap: MapboxMap
    private lateinit var permissionManager: PermissionsManager
    private var locationEngine: LocationEngine? = null
    private var locationComponent: LocationComponent? = null
    private val callback =
        LocationListeningCallback(
            this
        )

    var originLocation: Location? = null
    var navigationMapRoute: NavigationMapRoute? = null
    var currentRoute: DirectionsRoute? = null

    private lateinit var tlAttributes: TimelineAttributes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(
            this,
            "pk.eyJ1IjoieW9oYW5lc2dyZSIsImEiOiJjazQxcTM0am4wM3M5M21vYjlobXZ2OWN0In0.hxBaTdK-D8a-4oQS2ENS5Q"
        )
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_delivery
        )
        mapView = binding.mapView
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync{map->
            mapboxMap = map
            mapboxMap.setStyle(Style.MAPBOX_STREETS
            ) {
                enableLocation(it)
            }
        }
        tlAttributes = TimelineAttributes(
            markerSize = dpToPx(20f),
            markerColor = getColorCompat(R.color.colorGrey500),
            markerInCenter = true,
            markerLeftPadding = dpToPx(0f),
            markerTopPadding = dpToPx(0f),
            markerRightPadding = dpToPx(0f),
            markerBottomPadding = dpToPx(0f),
            linePadding = dpToPx(2f),
            startLineColor = getColorCompat(R.color.colorAccent),
            endLineColor = getColorCompat(R.color.colorAccent),
            lineStyle = TimelineView.LineStyle.NORMAL,
            lineWidth = dpToPx(2f),
            lineDashWidth = dpToPx(4f),
            lineDashGap = dpToPx(2f)
        )
        setDataListItems()
        initRecyclerView()
        binding.apply {
            bottomSheetLayout.bottomSheetContent.btnSuratJalanUpload.setOnClickListener {
                startActivity(Intent(this@DeliveryActivity, UploadSuratJalanActivity::class.java))
            }
            bottomSheetLayout.bottomSheetContent.btnBiayaTambahanUpload.setOnClickListener {
                startActivity(
                    Intent(
                        this@DeliveryActivity,
                        UploadBiayaTambahanActivity::class.java
                    )
                )
            }
        }
        tlAttributes.orientation = Orientation.VERTICAL
    }

    private fun setDataListItems() {
        listItemTimeline.add(TimeLineModel("Item successfully delivered", "", OrderStatus.INACTIVE))
        listItemTimeline.add(
            TimeLineModel(
                "Courier is out to delivery your order",
                "2017-02-12 08:00",
                OrderStatus.ACTIVE
            )
        )
        listItemTimeline.add(
            TimeLineModel(
                "Item has reached courier facility at New Delhi",
                "2017-02-11 21:00",
                OrderStatus.COMPLETED
            )
        )
        listItemTimeline.add(
            TimeLineModel(
                "Item has been given to the courier",
                "2017-02-11 18:00",
                OrderStatus.COMPLETED
            )
        )
        listItemTimeline.add(
            TimeLineModel(
                "Item is packed and will dispatch soon",
                "2017-02-11 09:30",
                OrderStatus.COMPLETED
            )
        )
        listItemTimeline.add(
            TimeLineModel(
                "Order is being readied for dispatch",
                "2017-02-11 08:00",
                OrderStatus.COMPLETED
            )
        )
        listItemTimeline.add(
            TimeLineModel(
                "Order processing initiated",
                "2017-02-10 15:00",
                OrderStatus.COMPLETED
            )
        )
        listItemTimeline.add(
            TimeLineModel(
                "Order confirmed by seller",
                "2017-02-10 14:30",
                OrderStatus.COMPLETED
            )
        )
        listItemTimeline.add(
            TimeLineModel(
                "Order placed successfully",
                "2017-02-10 14:00",
                OrderStatus.COMPLETED
            )
        )
    }

    private fun initRecyclerView() {
        initAdapter()
        binding.bottomSheetLayout.bottomSheetContent.timeline.recyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            @SuppressLint("LongLogTag")
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //if(recyclerView.getChildAt(0).top < 0) dropshadow.setVisible() else dropshadow.setGone()
            }
        })
    }

    private fun initAdapter() {
        binding.bottomSheetLayout.bottomSheetContent.timeline.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DeliveryActivity, RecyclerView.VERTICAL, false)
            adapter = TimelineAdapter(listItemTimeline, tlAttributes)
        }
    }

    private fun enableLocation(loadedMapStyle: Style) {
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            initializeLocationComponent(loadedMapStyle)
            initializeLocationEngine()
        } else {
            permissionManager = PermissionsManager(this)
            permissionManager.requestLocationPermissions(this)
        }
    }

    @SuppressWarnings("MissingPermission")
    fun initializeLocationEngine() {
        locationEngine = LocationEngineProvider.getBestLocationEngine(this)
        val request = LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
                .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
                .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME).build()

        locationEngine!!.requestLocationUpdates(request, callback, mainLooper)
        locationEngine!!.getLastLocation(callback).run {
            checkLocation()
            originLocation?.run {
                println("Origin Location Not Null")
                val startPoint = Point.fromLngLat(longitude, latitude)
                val endPoint = Point.fromLngLat(-7.2781101, 112.7919547)
                getRoute(startPoint, endPoint)
            }
        }
    }

    @SuppressWarnings("MissingPermission")
    fun initializeLocationComponent(loadedMapStyle: Style) {
        // Get an instance of the component
        locationComponent = mapboxMap.locationComponent
        // Set the LocationComponent activation options
        val locationComponentActivationOptions =
        LocationComponentActivationOptions.builder(this, loadedMapStyle)
            .useDefaultLocationEngine(false)
            .build()

        // Activate with the LocationComponentActivationOptions object
        locationComponent!!.activateLocationComponent(locationComponentActivationOptions)

        // Enable to make component visible
        locationComponent!!.isLocationComponentEnabled = true

        // Set the component's camera mode
        locationComponent!!.cameraMode = CameraMode.TRACKING

        // Set the component's render mode
        locationComponent!!.renderMode = RenderMode.COMPASS

        locationComponent!!.zoomWhileTracking(15.0)
    }

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
        Snackbar.make(
            binding.root,
            "This app needs location permission to be able to show your location on the map",
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            if (mapboxMap.style != null) {
                enableLocation(mapboxMap.style!!)
            }
        } else {
            Snackbar.make(binding.root, "User location was not granted", Snackbar.LENGTH_LONG)
                .show()
            finish()
        }
    }

    @SuppressWarnings("MissingPermission")
    override fun onStart() {
        super.onStart()
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            locationComponent?.onStart()
        }
        mapView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStop() {
        super.onStop()
        locationComponent?.onStop()
        mapView?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        locationEngine?.removeLocationUpdates(callback)
        mapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (outState != null) {
            mapView?.onSaveInstanceState(outState)
        }
    }

    private class LocationListeningCallback internal constructor(val activity: DeliveryActivity) :
        LocationEngineCallback<LocationEngineResult> {
        private val activityWeakReference = WeakReference(activity)
        override fun onSuccess(result: LocationEngineResult) {
            if (activity != null) {
                val location = result.lastLocation ?: return
                // Create a Toast which displays the new location's coordinates
                Snackbar.make(
                    activity.binding.root,
                    "New lat: ${result.lastLocation!!.latitude} New longitude: ${result.lastLocation!!.longitude}",
                    Snackbar.LENGTH_SHORT
                ).show()
                // Pass the new location to the Maps SDK's LocationComponent
                if (activity.mapboxMap != null && result.lastLocation != null) {
                    activity.mapboxMap.locationComponent.forceLocationUpdate(result.lastLocation)
                }
                result.lastLocation
            }
        }
        /**
         * The LocationEngineCallback interface's method which fires when the device's location can not be captured
         *
         * @param exception the exception message
         */
        override fun onFailure(exception: Exception) {
            Timber.d(exception.localizedMessage)
            val activity = activityWeakReference.get()
            if (activity != null) {
                Snackbar.make(activity.binding.root, exception.localizedMessage,
                    Snackbar.LENGTH_SHORT).show()
            }
        }

    }

    @SuppressLint("MissingPermission")
    private fun checkLocation() {
        if (originLocation == null) {
            mapboxMap.locationComponent.lastKnownLocation?.run {
                originLocation = this
            }
        }
    }

    private fun getRoute(originPoint: Point, endPoint: Point) {
        NavigationRoute.builder(this)
            .accessToken(Mapbox.getAccessToken()!!)
            .origin(originPoint)
            .destination(endPoint)
            .build()
            .getRoute(object : Callback<DirectionsResponse> {
                override fun onFailure(call: Call<DirectionsResponse>, t: Throwable) {
                    Timber.d(t.localizedMessage)
                }

                override fun onResponse(call: Call<DirectionsResponse>,
                                        response: Response<DirectionsResponse>
                ) {
                    if (navigationMapRoute != null) {
                        navigationMapRoute?.updateRouteVisibilityTo(false)
                    } else {
                        navigationMapRoute = NavigationMapRoute(binding.mapView, mapboxMap, null)
                    }
                    currentRoute = response.body()?.routes()?.first()
                    if (currentRoute != null) {
                        navigationMapRoute?.addRoute(currentRoute)
                    }
                }
            })
    }

}
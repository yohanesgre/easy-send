package com.example.easysend.features.delivery.view

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.example.easysend.R
import com.example.easysend.databinding.FragmentDeliveryBinding
import com.example.easysend.di.Injectable
import com.example.easysend.extentions.dpToPx
import com.example.easysend.extentions.getColorCompat
import com.example.easysend.features.darurat.DaruratDeliveryActivity
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

class DeliveryFragment : Fragment(), Injectable, PermissionsListener {

    private val listItemTimeline = ArrayList<TimeLineModel>()
    private var buttonState = 0
    private lateinit var timelineAdapter: TimelineAdapter
    private lateinit var binding: FragmentDeliveryBinding

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
            requireContext(),
            "pk.eyJ1IjoieW9oYW5lc2dyZSIsImEiOiJjazQxcTM0am4wM3M5M21vYjlobXZ2OWN0In0.hxBaTdK-D8a-4oQS2ENS5Q"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeliveryBinding.inflate(inflater, container, false)
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
            markerColor = getColorCompat(R.color.colorPrimary),
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
        setDataEmptyItemList()
        initRecyclerView()
        binding.apply {
            bottomSheetLayout.bottomSheetContent.btnSuratJalanUpload.setOnClickListener {
                root.findNavController().navigate(DeliveryFragmentDirections.actionDeliveryFragmentToUploadSuratJalanFragment())
            }
            bottomSheetLayout.bottomSheetContent.btnBiayaTambahanUpload.setOnClickListener {
                root.findNavController().navigate(DeliveryFragmentDirections.actionDeliveryFragmentToUploadBiayaTambahanFragment())
            }
            bottomSheetLayout.bottomSheetContent.btnDetilOrder.setOnClickListener {
                root.findNavController().navigate(DeliveryFragmentDirections.actionDeliveryFragmentToDeliveryDetilFragment())
            }
            bottomSheetLayout.bottomSheetContent.btnBiayaTambahanLihat.setOnClickListener {
                root.findNavController().navigate(DeliveryFragmentDirections.actionDeliveryFragmentToLihatBiayaTambahanFragment())
            }
            bottomSheetLayout.bottomSheetContent.btnSuratJalanLihat.setOnClickListener {
                root.findNavController().navigate(DeliveryFragmentDirections.actionDeliveryFragmentToLihatSuratJalanFragment())
            }
            bottomSheetLayout.bottomSheetContent.layoutSampaiGarasi.btnSampaiGarasi.setOnClickListener {
                root.findNavController().navigate(DeliveryFragmentDirections.actionDeliveryFragmentToDeliverySelesaiFragment())
            }
            val myItems = listOf("LAKA", "Ban Pecah", "Kendaraan Rusak", "HP Lowbat", "Lainnya")
            bottomSheetLayout.bottomSheetContent.btnEmergency.setOnClickListener{
                MaterialDialog(requireContext()).show {
                    listItems(items = myItems) { dialog, index, text ->
                        startActivity(Intent(requireActivity(), DaruratDeliveryActivity::class.java))
                    }
                }
            }
        }
        tlAttributes.orientation = Orientation.VERTICAL
        return binding.root
    }

    private fun setDataEmptyItemList(){
        listItemTimeline.add(TimeLineModel("Start dari Garasi", "2019-12-12 07:00", OrderStatus.COMPLETED))
        listItemTimeline.add(TimeLineModel("Sampai Lokasi Load", "", OrderStatus.ACTIVE))
        listItemTimeline.add(TimeLineModel("Selesai Loading", "", OrderStatus.INACTIVE))
        listItemTimeline.add(TimeLineModel("Sampai Lokasi Unload 1", "", OrderStatus.INACTIVE))
        listItemTimeline.add(TimeLineModel("Selesai Unload 1", "", OrderStatus.INACTIVE))
        listItemTimeline.add(TimeLineModel("Sampai Lokasi Unload 2", "", OrderStatus.INACTIVE))
        listItemTimeline.add(TimeLineModel("Selesai Unload 2", "", OrderStatus.INACTIVE))
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
        timelineAdapter = TimelineAdapter(listItemTimeline, tlAttributes)
        binding.bottomSheetLayout.bottomSheetContent.timeline.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = timelineAdapter
        }
        initButtonTimeline()
    }

    private fun initButtonTimeline(){
        binding.bottomSheetLayout.bottomSheetContent.btnTimeline.setOnClickListener {
            when(buttonState){
                0 ->{
                    binding.bottomSheetLayout.bottomSheetContent.btnTimeline.text = "Sampai Lokasi Load"
                    listItemTimeline[1] = TimeLineModel("Sampai Lokasi Load", "2019-12-12 09:30", OrderStatus.COMPLETED)
                    timelineAdapter.updateList(listItemTimeline)
                    buttonState++
                    println("Button State: $buttonState")
                }
                1 ->{
                    binding.bottomSheetLayout.bottomSheetContent.btnTimeline.text = "Selesai Loading"
                    listItemTimeline[2]= TimeLineModel("Selesai Loading", "", OrderStatus.ACTIVE)
                    timelineAdapter.updateList(listItemTimeline)
                    buttonState++
                }
                2 ->{
                    binding.bottomSheetLayout.bottomSheetContent.btnTimeline.text = "Sampai Lokasi Unload 1"
                    listItemTimeline[2]= TimeLineModel("Selesai Loading", "2019-12-12 12:00", OrderStatus.COMPLETED)
                    listItemTimeline[3]= TimeLineModel("Sampai Lokasi Unload 1", "", OrderStatus.ACTIVE)
                    timelineAdapter.updateList(listItemTimeline)
                    buttonState++
                }
                3->{
                    binding.bottomSheetLayout.bottomSheetContent.btnTimeline.text = "Selesai Unloading 1"
                    listItemTimeline[3]= TimeLineModel("Sampai Lokasi Unload 1", "2019-12-12 19:00", OrderStatus.COMPLETED)
                    listItemTimeline[4]= TimeLineModel("Selesai Unloading 1", "", OrderStatus.ACTIVE)
                    timelineAdapter.updateList(listItemTimeline)
                    buttonState++
                }
                4->{
                    binding.bottomSheetLayout.bottomSheetContent.btnTimeline.text = "Sampai Lokasi Unload 2"
                    listItemTimeline[4]= TimeLineModel("Selesai Unloading 1", "2019-12-12 20:00", OrderStatus.COMPLETED)
                    listItemTimeline[5]= TimeLineModel("Sampai Lokasi Unloading 2", "", OrderStatus.ACTIVE)
                    timelineAdapter.updateList(listItemTimeline)
                    buttonState++
                }
                5->{
                    binding.bottomSheetLayout.bottomSheetContent.btnTimeline.text = "Selesai Unloading 2"
                    listItemTimeline[5]= TimeLineModel("Sampai Lokasi Unloading 2", "2019-12-12 21:00", OrderStatus.COMPLETED)
                    listItemTimeline[6]= TimeLineModel("Selesai Unloading 2", "", OrderStatus.ACTIVE)
                    timelineAdapter.updateList(listItemTimeline)
                    buttonState++
                }
                6->{
                    binding.bottomSheetLayout.bottomSheetContent.btnTimeline.text = "Menuju Garasi"
                    listItemTimeline[6]= TimeLineModel("Selesai Unloading 2", "2019-12-12 21:30", OrderStatus.COMPLETED)
                    timelineAdapter.updateList(listItemTimeline)
                    buttonState++
                }
                7->{
                    binding.bottomSheetLayout.bottomSheetContent.timeline.recyclerView.visibility = View.GONE
                    binding.bottomSheetLayout.bottomSheetContent.layoutSampaiGarasi.layoutSampaiGarasi.visibility = View.VISIBLE
                    binding.bottomSheetLayout.bottomSheetContent.btnTimeline.visibility = View.GONE
                }
            }
        }
    }

    private fun enableLocation(loadedMapStyle: Style) {
        if (PermissionsManager.areLocationPermissionsGranted(requireContext())) {
            initializeLocationComponent(loadedMapStyle)
            initializeLocationEngine()
        } else {
            permissionManager = PermissionsManager(this)
            permissionManager.requestLocationPermissions(requireActivity())
        }
    }

    @SuppressWarnings("MissingPermission")
    fun initializeLocationEngine() {
        locationEngine = LocationEngineProvider.getBestLocationEngine(requireContext())
        val request = LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
                .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
                .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME).build()

        locationEngine!!.requestLocationUpdates(request, callback, requireActivity().mainLooper)
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
        LocationComponentActivationOptions.builder(requireContext(), loadedMapStyle)
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
            requireActivity().finish()
        }
    }

    @SuppressWarnings("MissingPermission")
    override fun onStart() {
        super.onStart()
        if (PermissionsManager.areLocationPermissionsGranted(requireContext())) {
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

    private class LocationListeningCallback internal constructor(val fragment: DeliveryFragment) :
        LocationEngineCallback<LocationEngineResult> {
        private val activityWeakReference = WeakReference(fragment)
        override fun onSuccess(result: LocationEngineResult) {
            if (fragment != null) {
                val location = result.lastLocation ?: return
                // Create a Toast which displays the new location's coordinates
                Snackbar.make(
                    fragment.binding.root,
                    "New lat: ${result.lastLocation!!.latitude} New longitude: ${result.lastLocation!!.longitude}",
                    Snackbar.LENGTH_SHORT
                ).show()
                // Pass the new location to the Maps SDK's LocationComponent
                if (fragment.mapboxMap != null && result.lastLocation != null) {
                    fragment.mapboxMap.locationComponent.forceLocationUpdate(result.lastLocation)
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
        NavigationRoute.builder(requireContext())
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
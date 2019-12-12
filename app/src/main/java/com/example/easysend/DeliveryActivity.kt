package com.example.easysend

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.easysend.databinding.ActivityDeliveryBinding
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style


class DeliveryActivity : AppCompatActivity(), OnMapReadyCallback, PermissionsListener {

    private lateinit var binding: ActivityDeliveryBinding
    private var mapView: MapView? = null
    private lateinit var mapboxMap: MapboxMap
    private var permissionsManager: PermissionsManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, "pk.eyJ1IjoieW9oYW5lc2dyZSIsImEiOiJjazQxcTM0am4wM3M5M21vYjlobXZ2OWN0In0.hxBaTdK-D8a-4oQS2ENS5Q")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_delivery)
        mapView = binding.mapView
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync(this)
    }

    override fun onStart() {
        super.onStart()
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
        mapView?.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onMapReady(mapboxMap: MapboxMap) {
        this@DeliveryActivity.mapboxMap = mapboxMap

        mapboxMap.setStyle(
            Style.Builder().fromUri("mapbox://styles/mapbox/cjerxnqt3cgvp2rmyuxbeqme7")
        ) { style -> enableLocationComponent(style) }
    }

    private fun enableLocationComponent(loadedMapStyle: Style?) { // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) { // Get an instance of the component
            val locationComponent = mapboxMap.locationComponent
            // Activate with options
            locationComponent.activateLocationComponent(
                LocationComponentActivationOptions.builder(this, loadedMapStyle!!).build()
            )
            // Enable to make component visible
            locationComponent.isLocationComponentEnabled = true
            // Set the component's camera mode
            locationComponent.cameraMode = CameraMode.TRACKING
            // Set the component's render mode
            locationComponent.renderMode = RenderMode.COMPASS
        } else {
            permissionsManager = PermissionsManager(this)
            permissionsManager!!.requestLocationPermissions(this)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        permissionsManager?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
        Toast.makeText(this, "Location Permission needed!", Toast.LENGTH_LONG)
            .show()
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            mapboxMap.getStyle { style -> enableLocationComponent(style) }
        } else {
            Toast.makeText(this, "Location Permission not granted!", Toast.LENGTH_LONG)
                .show()
            finish()
        }
    }
}
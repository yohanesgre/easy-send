package com.example.easysend

import android.app.Application
import com.mapbox.mapboxsdk.Mapbox

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        Mapbox.getInstance(this, "pk.eyJ1IjoieW9oYW5lc2dyZSIsImEiOiJjazQxcTM0am4wM3M5M21vYjlobXZ2OWN0In0.hxBaTdK-D8a-4oQS2ENS5Q")
        Instance = this
    }

    companion object {
        lateinit var Instance: App
    }
}
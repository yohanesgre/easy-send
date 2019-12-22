package com.example.easysend

import android.app.Activity
import android.app.Application
import com.example.easysend.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        instance = this
    }
    companion object {
        lateinit var instance: App
    }
    override fun activityInjector() = dispatchingAndroidInjector
}
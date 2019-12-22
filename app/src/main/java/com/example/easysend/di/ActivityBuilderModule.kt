package com.example.easysend.di

import com.example.easysend.features.MainActivity
import com.example.easysend.features.delivery.DeliveryActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeDeliveryActivity(): DeliveryActivity
/*
    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun contributeRegisterActivity(): RegisterActivity

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity*/
}

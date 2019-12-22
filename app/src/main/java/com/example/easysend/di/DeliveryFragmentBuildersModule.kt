package com.example.easysend.di

import com.example.easysend.features.delivery.view.DeliveryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class DeliveryFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeDeliveryFragment(): DeliveryFragment
}

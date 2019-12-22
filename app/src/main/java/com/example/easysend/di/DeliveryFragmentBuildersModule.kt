package com.example.easysend.di

import com.example.easysend.features.delivery.view.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class DeliveryFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeDeliveryFragment(): DeliveryFragment

    @ContributesAndroidInjector
    abstract fun contributeDeliveryDetilFragment(): DeliveryDetilFragment

    @ContributesAndroidInjector
    abstract fun contributeDeliverySelesaiFragment(): DeliverySelesaiFragment

    @ContributesAndroidInjector
    abstract fun contributeUploadBiayaTambahanFragment(): UploadBiayaTambahanFragment

    @ContributesAndroidInjector
    abstract fun contributeUploadSuratJalanFragment(): UploadSuratJalanFragment

    @ContributesAndroidInjector
    abstract fun contributeLihatSuratJalanFragment(): LihatSuratJalanFragment

    @ContributesAndroidInjector
    abstract fun contributeLihatBiayaTambahanFragment(): LihatBiayaTambahanFragment
}

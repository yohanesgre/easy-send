package com.example.easysend.di

import com.example.easysend.features.home.view.HomeFragment
import com.example.easysend.features.komisi.view.KomisiFragment
import com.example.easysend.features.myorder.view.MyOrderChildFragment
import com.example.easysend.features.myorder.view.MyOrderFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeMyOrderFragment(): MyOrderFragment
    @ContributesAndroidInjector
    abstract fun contributeMyOrderChildFragment(): MyOrderChildFragment

    @ContributesAndroidInjector
    abstract fun contributeKomisiFragment(): KomisiFragment
}

package com.example.easysend.di

import com.example.easysend.features.home.view.HomeFragment
import com.example.easysend.features.komisi.view.KomisiChildFragment
import com.example.easysend.features.komisi.view.KomisiFragment
import com.example.easysend.features.myorder.view.MyOrderChildFragment
import com.example.easysend.features.myorder.view.MyOrderFragment
import com.example.easysend.features.onboarding.OnboardingFragment
import com.example.easysend.features.profile.ProfileFragment
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

    @ContributesAndroidInjector
    abstract fun contributeKomisiChildFragment(): KomisiChildFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeOnboardingFragment(): OnboardingFragment
}

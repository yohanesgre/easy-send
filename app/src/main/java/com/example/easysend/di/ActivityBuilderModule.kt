package com.example.easysend.di

import com.example.easysend.features.MainActivity
import com.example.easysend.features.SplashActivity
import com.example.easysend.features.darurat.DaruratDeliveryActivity
import com.example.easysend.features.delivery.DeliveryActivity
import com.example.easysend.features.login.LoginActivity
import com.example.easysend.features.notification.NotificationActivity
import com.example.easysend.features.profile.ProfileEditActivity
import com.example.easysend.features.shared.DetailOrderActiveActivity
import com.example.easysend.features.shared.DetailOrderDoneActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [DeliveryFragmentBuildersModule::class])
    abstract fun contributeDeliveryActivity(): DeliveryActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailOrderActiveActivity(): DetailOrderActiveActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailOrderDoneActivity(): DetailOrderDoneActivity

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun contributeNotificationActivity(): NotificationActivity

    @ContributesAndroidInjector
    abstract fun contributeProfileEditActivity(): ProfileEditActivity

    @ContributesAndroidInjector
    abstract fun contributeDaruratDeliveryActivity(): DaruratDeliveryActivity
/*
    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun contributeRegisterActivity(): RegisterActivity

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity*/
}

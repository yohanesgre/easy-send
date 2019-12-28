package com.example.easysend.di

import com.example.easysend.features.onboarding.OnboardingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class OnboardingFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeOnboardingFragment(): OnboardingFragment
}

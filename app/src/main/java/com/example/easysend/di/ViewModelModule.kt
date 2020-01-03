package com.example.easysend.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.easysend.features.delivery.viewmodel.DeliveryDetailViewModel
import com.example.easysend.features.delivery.viewmodel.DeliveryViewModel
import com.example.easysend.features.home.viewmodel.HomeViewModel
import com.example.easysend.features.login.viewmodel.LoginViewModel
import com.example.easysend.features.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DeliveryViewModel::class)
    abstract fun bindDeliveryViewModel(viewModel: DeliveryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DeliveryDetailViewModel::class)
    abstract fun bindDeliveryDetailViewModel(viewModel: DeliveryDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

package com.example.easysend.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class ViewModelModule {
    /*@Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel*/

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

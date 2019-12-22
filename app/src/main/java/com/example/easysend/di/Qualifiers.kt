package com.example.easysend.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class NonAuthAPI

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class AuthAPI

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CoroutineScropeIO

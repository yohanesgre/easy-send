package com.example.easysend.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.easysend.di.UserCache
import com.example.easysend.network.api.AuthRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val repository: AuthRepository,
    val userCache: UserCache
)
    : ViewModel() {
    val authStatus = liveData(Dispatchers.IO) { emit( repository.getAuthTokenAccess()) }
}
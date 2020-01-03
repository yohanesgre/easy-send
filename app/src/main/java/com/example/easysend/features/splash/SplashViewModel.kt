package com.example.easysend.features.splash

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.easysend.di.UserCache
import com.example.easysend.network.api.AuthRepository
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val repository: AuthRepository,
    val userCache: UserCache
)
    : ViewModel() {

    private val appNoHp =  userCache.getNoHP()

    private fun checkUserCacheExist():Boolean{
        return appNoHp.isNotEmpty()
    }
    val mediator = MediatorLiveData<Unit>()
    val authStatus by lazy {
        repository.checkAuthTokenAccess()
    }

    override fun onCleared() {
        super.onCleared()
        repository.completableJob.cancel()
    }
}
package com.example.easysend.features.login.data.datasource

import com.example.easysend.network.api.BaseDataSource
import com.example.easysend.network.api.EasySendService
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(private val service: EasySendService) :
    BaseDataSource() {
    suspend fun login(nohp: String, password: String) =
        getResult { service.login(nohp, password) }
}

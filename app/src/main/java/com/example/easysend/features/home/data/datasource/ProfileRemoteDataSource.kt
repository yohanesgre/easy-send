package com.example.easysend.features.home.data.datasource

import com.example.easysend.network.api.BaseDataSource
import com.example.easysend.network.api.EasySendService
import javax.inject.Inject

class ProfileRemoteDataSource @Inject constructor(private val service: EasySendService) :
    BaseDataSource() {
    suspend fun fetch() = getResult { service.getProfile() }
}

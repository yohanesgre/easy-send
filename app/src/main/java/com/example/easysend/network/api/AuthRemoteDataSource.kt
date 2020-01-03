package com.example.easysend.network.api

import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val service: EasySendService) :
    BaseDataSource() {
    suspend fun fetch() = getResult { service.getProfile() }
}

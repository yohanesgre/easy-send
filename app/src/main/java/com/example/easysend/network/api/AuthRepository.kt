package com.example.easysend.network.api

import com.example.easysend.di.UserCache
import com.example.easysend.features.login.data.datasource.LoginRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val dataSource: LoginRemoteDataSource,
    private val userCache: UserCache
){
    private val appNoHp =  userCache.getNoHP()
    private val appPassword =  userCache.getPassword()
    private val appTokenAccess =  userCache.getTokenAccess()

    suspend fun getAuthTokenAccess() = dataSource.login(appNoHp, appPassword)

    fun refreshAccessToken(newToken:String):String{
        userCache.putTokenAccess(newToken)
        return appTokenAccess
    }
}
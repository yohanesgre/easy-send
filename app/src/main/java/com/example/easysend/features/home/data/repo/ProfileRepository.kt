package com.example.easysend.features.home.data.repo

import com.example.easysend.di.UserCache
import com.example.easysend.features.home.data.datasource.ProfileRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    private val remoteDataSource: ProfileRemoteDataSource,
    private val userCache: UserCache
) {
    suspend fun getProfileResult() = remoteDataSource.fetch()
}
package com.example.easysend.features.home.data.repo

import com.example.easysend.di.UserCache
import com.example.easysend.features.home.data.datasource.OrderRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepository @Inject constructor(
    private val remoteDataSource: OrderRemoteDataSource,
    private val userCache: UserCache
) {
    suspend  fun getOrderTerbaruResult() = remoteDataSource.fetch()
}
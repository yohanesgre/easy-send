package com.example.easysend.features.delivery.data.repo

import com.example.easysend.features.delivery.data.datasource.OrderDetailRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderDetailRepository @Inject constructor(
    private val remoteDataSource: OrderDetailRemoteDataSource
) {
    suspend  fun getOrderDetailId(id:Int) = remoteDataSource.fetch(id)
    suspend fun getCheckPoints(id: Int) = remoteDataSource.fetchCheckPoints(id)
}
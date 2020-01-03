package com.example.easysend.features.delivery.data.datasource

import com.example.easysend.network.api.BaseDataSource
import com.example.easysend.network.api.EasySendService
import javax.inject.Inject

class OrderDetailRemoteDataSource @Inject constructor(private val service: EasySendService) :
    BaseDataSource() {
    suspend fun fetch(id:Int) = getResult { service.getOrderDetail(id) }
    suspend fun fetchCheckPoints(id:Int) = getResult { service.getOrderDetailCheckPoints(id) }
}

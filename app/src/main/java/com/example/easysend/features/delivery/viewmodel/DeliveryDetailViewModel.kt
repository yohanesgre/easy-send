package com.example.easysend.features.delivery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.easysend.features.delivery.data.repo.OrderDetailRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class DeliveryDetailViewModel @Inject constructor(
    private val repo: OrderDetailRepository
) : ViewModel(){
    var id: Int = 0
    val resultOrderDetail = liveData(Dispatchers.IO) { emit(repo.getOrderDetailId(id))}
}
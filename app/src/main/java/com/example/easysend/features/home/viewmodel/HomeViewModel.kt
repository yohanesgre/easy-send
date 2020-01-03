package com.example.easysend.features.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.easysend.features.home.data.repo.OrderRepository
import com.example.easysend.features.home.data.repo.ProfileRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repoProfile: ProfileRepository,
    private val repoOrder: OrderRepository
) : ViewModel(){
    var selectedOrderId = 0

    val resultProfile = liveData(Dispatchers.IO) { emit(repoProfile.getProfileResult())}
    val resultOrderTerbaru = liveData(Dispatchers.IO) { emit(repoOrder.getOrderTerbaruResult())}
}
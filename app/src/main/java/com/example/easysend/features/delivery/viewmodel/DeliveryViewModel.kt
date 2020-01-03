package com.example.easysend.features.delivery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.easysend.features.delivery.data.repo.OrderDetailRepository
import com.example.easysend.network.response.delivery.CheckPoint
import com.github.vipulasri.timelineview.sample.model.OrderStatus
import com.github.vipulasri.timelineview.sample.model.TimeLineModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DeliveryViewModel @Inject constructor(
    private val repo: OrderDetailRepository
) : ViewModel(){
    var id: Int = 0
    val resultOrderDetail = liveData(Dispatchers.IO) { emit(repo.getOrderDetailId(id))}
    val resultCheckPoint = liveData(Dispatchers.IO) { emit(repo.getCheckPoints(id))}
    val timeLineContent = MutableLiveData<List<CheckPoint>>()
    val buttonState = MutableLiveData<Int>().apply{
        value = 0
    }
    val timelineContentStatic = MutableLiveData<List<TimeLineModel>>().apply {
        value = listOf(
            TimeLineModel("Start dari Garasi", "2019-12-12 07:00", OrderStatus.COMPLETED),
            TimeLineModel("Sampai Lokasi Load", "", OrderStatus.ACTIVE),
            TimeLineModel("Selesai Loading", "", OrderStatus.INACTIVE),
            TimeLineModel("Sampai Lokasi Unload 1", "", OrderStatus.INACTIVE),
            TimeLineModel("Selesai Unload 1", "", OrderStatus.INACTIVE),
            TimeLineModel("Sampai Lokasi Unload 2", "", OrderStatus.INACTIVE),
            TimeLineModel("Selesai Unload 2", "", OrderStatus.INACTIVE)
        )
    }

    fun updateButtonState(newState:Int){
        buttonState.postValue(newState)
    }
}
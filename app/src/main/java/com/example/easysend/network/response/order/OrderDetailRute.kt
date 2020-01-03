package com.example.easysend.network.response.order

data class OrderDetailRute(
    val id:Int,
    val order_id:Int,
    val alamat:String,
    val lat:String,
    val lon:String,
    val created_at:String,
    val updated_at:String
)
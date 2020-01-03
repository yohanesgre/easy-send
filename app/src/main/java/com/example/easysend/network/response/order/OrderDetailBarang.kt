package com.example.easysend.network.response.order

data class OrderDetailBarang(
    val id:Int,
    val order_id:Int,
    val deskripsi:String,
    val created_at:String,
    val updated_at:String
)
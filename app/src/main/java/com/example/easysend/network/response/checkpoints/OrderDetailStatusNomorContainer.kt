package com.example.easysend.network.response.checkpoints

data class OrderDetailStatusNomorContainer(
    val id:Int,
    val orderdetailstatus_id:Int,
    val nomor_container_id:Int,
    val created_at:String,
    val updated_at:String,
    val deleted_at:String?
)
package com.example.easysend.network.response.delivery

data class CheckPoint (
    val id:Int,
    val order_detail_status_id:Int,
    val status:String,
    val tanggal:String,
    val jam:String,
    val lon:String,
    val lat:String,
    val created_at:String,
    val updated_at:String,
    val deleted_at:String?
)
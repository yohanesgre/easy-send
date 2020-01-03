package com.example.easysend.network.response.profile

data class DriverTruckResponse(
    val id:Int,
    val user_id:Int,
    val company_id:Int,
    val container_id:Int,
    val no_polisi:String,
    val status_order:String,
    val status_tersedia:Int,
    val deleted_at:String?,
    val created_at:String,
    val updated_at:String
)
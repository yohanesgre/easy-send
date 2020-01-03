package com.example.easysend.network.response.order

data class Container(
    val id:Int,
    val ukuran:String,
    val path_container:String?,
    val deleted_at:String?,
    val created_at:String,
    val updated_at:String
)
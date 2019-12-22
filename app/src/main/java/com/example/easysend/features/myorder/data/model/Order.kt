package com.example.easysend.features.myorder.data.model

data class Order(
    val date:String,
    val origin:String,
    val destination:String,
    val finished: Boolean
)
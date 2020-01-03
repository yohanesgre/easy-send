package com.example.easysend.network.response.order

data class OrderResponse (
    val id:Int,
    val order_id:Int,
    val driver_truk_id:Int,
    val nomor_surat_jalan:String,
    val status:String,
    val status_order:String?,
    val path_tanda_tangan_security:String,
    val nama_security:String?,
    val nama_penerima:String?,
    val ttd_penerima:String,
    val nama_pengirim:String?,
    val ttd_pengirim:String,
    val nama_pembawa:String?,
    val ttd_pembawa:String,
    val ratting:Int?,
    val created_at:String,
    val updated_at:String,
    val deleted_at:String?,
    val order: Order
)
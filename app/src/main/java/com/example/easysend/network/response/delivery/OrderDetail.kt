package com.example.easysend.network.response.delivery

import com.example.easysend.network.response.order.Container
import com.example.easysend.network.response.order.OrderDetailBarang
import com.example.easysend.network.response.order.OrderDetailRute

data class OrderDetail(
    val id:Int,
    val user_id:Int,
    val company_id:Int,
    val container_id:Int,
    val asal_alamat:String,
    val asal_lat:String,
    val asal_lon:String,
    val jumlah_truk:String,
    val nama_kapal:String,
    val pengiriman_tanggal:String,
    val pengiriman_waktu:String,
    val estimasi_berat: String,
    val no_telepon_pengurus: String,
    val jenis_perjalanan:String,
    val harga_penawaran: Double,
    val status_pembayaran: Int,
    val status:String,
    val order_serndiri:Int,
    val timer:String?,
    val verified:Int,
    val created_at:String,
    val updated_at:String,
    val deleted_at:String?,
    val jumlah_truk_diambil:Int,
    val jumlah_order_selesai:Int,
    val order_detail_rutes:List<OrderDetailRute>,
    val order_detail_barangs:List<OrderDetailBarang>,
    val container: Container
)
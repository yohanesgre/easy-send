package com.example.easysend.network.response.profile

data class CompanyResponse(
    val id:Int,
    val nama_perusahaan:String,
    val no_telepon:String,
    val path_logo:String,
    val is_shipper:Int,
    val verified:Int,
    val path_foto_ktp:String,
    val path_npwp:String,
    val path_siup:String,
    val path_tdp:String,
    val path_bank_account:String,
    val deleted_at:String?,
    val created_at:String,
    val updated_at:String
)
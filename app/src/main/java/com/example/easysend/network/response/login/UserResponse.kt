package com.example.easysend.network.response.login

data class UserResponse(
    val id:Int,
    val name:String,
    val no_telepon: String,
    val no_telepon_verified_at: String?,
    val jabatan: String,
    val path_foto_ktp: String,
    val email: String,
    val email_verified_at:String,
    val remember_token: String?,
    val deleted_at: String?,
    val created_at: String,
    val updated_at: String
)
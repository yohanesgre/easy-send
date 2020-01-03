package com.example.easysend.network.response

data class LoginResponse(
    val user: UserResponse,
    val access_token: String,
    val token_type: String,
    val expires_at: String
)
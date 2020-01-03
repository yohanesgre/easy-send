package com.example.easysend.network.response

data class ProfileResponse (
    val user: UserResponse,
    val company: CompanyResponse,
    val driver_truks: DriverTruckResponse
)
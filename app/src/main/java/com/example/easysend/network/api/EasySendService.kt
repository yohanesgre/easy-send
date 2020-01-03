package com.example.easysend.network.api

import com.example.easysend.network.response.LoginResponse
import com.example.easysend.network.response.ProfileResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface EasySendService{
    @FormUrlEncoded
    @POST("login/driver")
    suspend fun login(
        @Field("no_telepon") noTelp: String,
        @Field("password") password: String
    ): Response<ResultResponse<LoginResponse>>

    @GET("driver/profile")
    suspend fun getProfile(): Response<ResultResponse<ProfileResponse>>
}
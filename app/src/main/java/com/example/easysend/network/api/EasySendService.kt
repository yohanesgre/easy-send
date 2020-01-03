package com.example.easysend.network.api

import com.example.easysend.network.response.delivery.OrderDetailResponse
import com.example.easysend.network.response.login.LoginResponse
import com.example.easysend.network.response.order.OrderResponse
import com.example.easysend.network.response.profile.ProfileResponse
import retrofit2.Response
import retrofit2.http.*

interface EasySendService{
    @FormUrlEncoded
    @POST("login/driver")
    suspend fun login(
        @Field("no_telepon") noTelp: String,
        @Field("password") password: String
    ): Response<ResultResponse<LoginResponse>>

    @GET("driver/profile")
    suspend fun getProfile(): Response<ResultResponse<ProfileResponse>>

    @GET("driver/order/terbaru")
    suspend fun getOrderTerbaru(): Response<ResultResponse<OrderResponse>>

    @GET("driver/order-detail/{id}")
    suspend fun getOrderDetail(@Path("id") id:Int):Response<ResultResponse<OrderDetailResponse>>

    @GET("driver/order-detail/{id}/checkpoint")
    suspend fun getOrderDetailCheckPoints(@Path("id") id:Int):Response<ResultResponse<OrderDetailResponse>>

}
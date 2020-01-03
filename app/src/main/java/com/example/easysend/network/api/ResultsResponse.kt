package com.example.easysend.network.api

data class ResultResponse<T>(
    val status: Boolean,
    val message: String,
    val data: T
    /*
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("data")
    val data: List<T>,
    @SerializedName("first_page_url")
    val firstPageUrl: String? = null,
    @SerializedName("from")
    val from: Int,
    @SerializedName("last_page")
    val lastPage: Int,
    @SerializedName("last_page_url")
    val lastPageUrl: String? = null,
    @SerializedName("next_page_url")
    val nextPageUrl: String? = null,
    @SerializedName("path")
    val path: String? = null,
    @SerializedName("per_page")
    val per_page: Int,
    @SerializedName("prev_page_url")
    val prevPageUrl: String? = null,
    @SerializedName("to")
    val to: Int,
    @SerializedName("total")
    val total: Int*/
)
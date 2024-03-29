package com.teaml.model

import com.google.gson.annotations.SerializedName


data class ApiResult<T>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_result")
    val totalResult: Int,
    @SerializedName("videos")
    val videos: List<T>
)
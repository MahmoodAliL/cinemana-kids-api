package com.teaml.model

import com.google.gson.annotations.SerializedName

data class Discover(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("cover_img")
    val coverImg: String,
    @SerializedName("type")
    val type: String
)
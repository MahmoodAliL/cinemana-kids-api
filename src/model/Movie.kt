package com.teaml.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("cover_img")
    val coverImg: String,
    @SerializedName("video_url")
    val videoUrl: String
)



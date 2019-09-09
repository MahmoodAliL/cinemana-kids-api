package com.teaml.model

import com.google.gson.annotations.SerializedName

data class Episode(
    @SerializedName("id")
    val id: Int,
    @SerializedName("series_id")
    val seriesId: Int,
    @SerializedName("episode")
    val episode: Int,
    @SerializedName("video_url")
    val videoUrl: String
)
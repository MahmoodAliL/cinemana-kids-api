package com.teaml.model

import com.google.gson.annotations.SerializedName

data class Series(
    @SerializedName("id")
    val id: Int = -1,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("cover_img")
    val coverImg: String = "",
    @SerializedName("episode_count")
    val episodeCount: Int = -1
)
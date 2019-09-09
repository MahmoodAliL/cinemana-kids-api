package com.teaml.model.table

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Table

object TbSeriesEpisode : IntIdTable("series_episode") {
    val seriesId = integer("series_id").uniqueIndex()
    val episode = integer("episode")
    val videoUrl = varchar("video_url", 255)
}
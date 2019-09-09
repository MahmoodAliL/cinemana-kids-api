package com.teaml.model.table

import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime


object TbSeries : Table("series") {
    val id = integer("id").primaryKey()
    val title = varchar("title", 255)
    val coverImg = varchar("cover_img", 255)
    val episodeCount = integer("episode_count")
    val uploadDate = DateTime()
}
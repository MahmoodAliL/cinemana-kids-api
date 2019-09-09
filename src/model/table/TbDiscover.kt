package com.teaml.model.table

import org.jetbrains.exposed.sql.Table

object TbDiscover : Table("discover") {
    val id = integer("id")
    val title = varchar("title", 255)
    val coverImg = varchar("cover_img", 255)
    val type = varchar("type", 50)
}

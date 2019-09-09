package model.table;

import org.jetbrains.exposed.sql.Table

object TbMovie : Table("movie") {
    val id = integer("id").primaryKey().autoIncrement()
    val title = varchar("title", 50)
    val coverImg = varchar("cover_img", 255)
    val videoUrl = varchar("video_url", 255)
    val uploadDate = datetime("upload_date")
}

package com.teaml.service

import com.teaml.model.ApiResult
import com.teaml.model.Movie
import com.teaml.utils.Constant.ITEM_IN_PAGE
import com.teaml.utils.dbQuery
import com.teaml.utils.firstOrElse
import model.table.TbMovie
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class MovieService {

    suspend fun fetchMovie(id: Int): Movie = dbQuery {
        TbMovie.select { TbMovie.id eq id }.map { toMovie(it) }.firstOrElse { emptyMovie() }
    }

    private fun toMovie(row: ResultRow): Movie {
        return Movie(
            row[TbMovie.id],
            row[TbMovie.title],
            row[TbMovie.coverImg],
            row[TbMovie.videoUrl]
        )
    }

    private fun emptyMovie() = Movie(-1, "", "", "")
}
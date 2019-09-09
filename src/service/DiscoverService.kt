package com.teaml.service

import com.teaml.base.BaseService
import com.teaml.model.ApiResult
import com.teaml.model.Discover
import com.teaml.model.table.TbDiscover
import com.teaml.utils.dbQuery
import com.teaml.utils.getTotalPage
import com.teaml.utils.selectPage
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.andWhere

class DiscoverService : BaseService() {

    private enum class VideoType {
        SERIES,
        MOVIE
    }

    suspend fun fetchMoviesAndSeries(page: Int) = dbQuery {
        val latestMoviesAndSeries = TbDiscover.selectPage(page).map { toDiscover(it) }

        val totalPages = TbDiscover.getTotalPage()
        val totalResult = latestMoviesAndSeries.count()

        ApiResult(page, totalPages, totalResult, latestMoviesAndSeries)
    }

    suspend fun fetchMovies(page: Int) = dbQuery {
        val moviesCondition = TbDiscover.type eq VideoType.MOVIE.name

        val latestMovie = TbDiscover.selectPage(page)
            .andWhere { moviesCondition }
            .map { toDiscover(it) }

        val totalPages = TbDiscover.getTotalPage { moviesCondition }
        val totalResult = latestMovie.count()

        ApiResult(page, totalPages, totalResult, latestMovie)
    }

    suspend fun fetchSeries(page: Int) = dbQuery {
        val seriesCondition = TbDiscover.type eq VideoType.SERIES.name

        val latestSeries = TbDiscover.selectPage(page)
            .andWhere { seriesCondition }
            .map { toDiscover(it) }

        val totalPages = TbDiscover.getTotalPage { seriesCondition }
        val totalResult = latestSeries.count()

        ApiResult(page, totalPages, totalResult, latestSeries)
    }

    private fun toDiscover(it: ResultRow): Discover {
        return Discover(
            it[TbDiscover.id],
            it[TbDiscover.title],
            it[TbDiscover.coverImg],
            it[TbDiscover.type]
        )
    }
}
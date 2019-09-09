package com.teaml.service

import com.teaml.model.ApiResult
import com.teaml.model.Episode
import com.teaml.model.Series
import com.teaml.model.table.TbSeries
import com.teaml.model.table.TbSeriesEpisode
import com.teaml.utils.dbQuery
import com.teaml.utils.firstOrElse
import com.teaml.utils.getTotalPage
import com.teaml.utils.selectPage
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.select

class SeriesService {

    suspend fun fetchSeries(id: Int): Series = dbQuery {
        TbSeries.select { TbSeries.id eq id }
            .map { toSeries(it) }.firstOrElse { emptySeries() }
    }

    private fun toSeries(row: ResultRow): Series =
        Series(row[TbSeries.id], row[TbSeries.title], row[TbSeries.coverImg], row[TbSeries.episodeCount])

    private fun emptySeries() = Series()

    suspend fun fetchEpisodes(seriesId: Int, page: Int): ApiResult<Episode> = dbQuery {

        val episodes = TbSeriesEpisode.selectPage(page)
            .andWhere { TbSeriesEpisode.seriesId eq seriesId }
            .orderBy(TbSeriesEpisode.episode)
            .map { toEpisode(it) }

        val totalPages = TbSeriesEpisode.getTotalPage { TbSeriesEpisode.seriesId eq seriesId  }
        val totalResult = episodes.count()

        ApiResult(page, totalPages, totalResult, episodes)
    }

    private fun toEpisode(row: ResultRow): Episode =
        Episode(
            row[TbSeriesEpisode.id].value,
            row[TbSeriesEpisode.seriesId],
            row[TbSeriesEpisode.episode],
            row[TbSeriesEpisode.videoUrl]
        )

}
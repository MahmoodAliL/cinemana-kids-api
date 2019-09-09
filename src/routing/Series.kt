package com.teaml.routing

import com.teaml.service.SeriesService
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import java.lang.IllegalStateException

fun Route.series(seriesService: SeriesService) {

    route("/series") {

        get("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide series id")
            val series = seriesService.fetchSeries(id)
            call.respond(series)
        }

        get("/{id}/episode/{page}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide series id")
            val page = call.parameters["page"]?.toInt() ?: throw IllegalStateException("Must provide series id")
            val episodes = seriesService.fetchEpisodes(id, page)
            call.respond(episodes)
        }
    }
}
package com.teaml.routing

import com.teaml.service.DiscoverService
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import java.lang.IllegalStateException


fun Route.discover(discoverService: DiscoverService) {
    route("/discover") {

        get("/{page}") {
            val page = call.parameters["page"]?.toInt() ?: throw IllegalStateException("Must provide page number")
            val moviesAndSeries = discoverService.fetchMoviesAndSeries(page)
            call.respond(moviesAndSeries)
        }

        get("/movie/{page}") {
            val page = call.parameters["page"]?.toInt() ?: throw IllegalStateException("Must provide page number")
            val movies = discoverService.fetchMovies(page)
            call.respond(movies)
        }

        get("/series/{page}") {
            val page = call.parameters["page"]?.toInt() ?: throw IllegalStateException("Must provide page number")
            val series = discoverService.fetchSeries(page)
            call.respond(series)
        }

    }
}
package com.teaml.routing

import com.teaml.service.MovieService
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import java.lang.IllegalStateException

fun Route.movie(movieService: MovieService) {
    route("/movie") {

        get("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide movie id")
            val movie = movieService.fetchMovie(id)
            call.respond(movie)
        }
    }
}
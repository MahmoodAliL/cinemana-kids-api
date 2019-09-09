package com.teaml

import com.teaml.routing.discover
import com.teaml.routing.movie
import com.teaml.routing.series
import com.teaml.service.DiscoverService
import com.teaml.service.MovieService
import com.teaml.service.SeriesService
import com.teaml.utils.DatabaseUtils
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.gson.gson
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.routing


fun Application.module() {


    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    DatabaseUtils.connect()

    val discoverService = DiscoverService()
    val seriesService = SeriesService()
    val movieService = MovieService()

    install(Routing) {
        discover(discoverService)
        series(seriesService)
        movie(movieService)
    }

}

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)


package io.morimanga.riku.config

import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*
import io.morimanga.riku.presentation.routing.addonsRouting
import io.morimanga.riku.presentation.routing.foldersRouting

fun Application.routing() {
    install(Resources)
    routing {
        foldersRouting()
        addonsRouting()
    }
}
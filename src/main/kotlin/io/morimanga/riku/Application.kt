package io.morimanga.riku

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.morimanga.riku.config.cors
import io.morimanga.riku.config.database
import io.morimanga.riku.config.routing
import io.morimanga.riku.config.serialization

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    database()
    cors()
    serialization()
    routing()
}
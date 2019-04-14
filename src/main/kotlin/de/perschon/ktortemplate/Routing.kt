package de.perschon.ktortemplate

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.response.header
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.util.pipeline.PipelineContext

fun routes(): Routing.() -> Unit {
    return {
        get("/health", health())
    }
}

fun health(): suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit {
    return {
        call.respond(Health())
    }
}

data class Health(
    val healthy: Boolean = true
)

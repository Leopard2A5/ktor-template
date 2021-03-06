package de.perschon.ktortemplate

import de.perschon.ktortemplate.config.KonfKtorConfig
import de.perschon.ktortemplate.config.Ktor
import de.perschon.ktortemplate.config.callId
import de.perschon.ktortemplate.config.callLogging
import de.perschon.ktortemplate.config.contentNegotiation
import de.perschon.ktortemplate.config.loadConfig
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallId
import io.ktor.features.CallLogging
import io.ktor.features.Compression
import io.ktor.features.ContentNegotiation
import io.ktor.routing.routing
import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    val config = loadConfig()

    val env = applicationEngineEnvironment {
        this.config = KonfKtorConfig(config = config)
        connector {
            host = config[Ktor.Deployment.host]
            port = config[Ktor.Deployment.port]
        }
    }

    embeddedServer(Netty, env).start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation, contentNegotiation)
    install(CallId, callId)
    install(CallLogging, callLogging)
    install(Compression)

    routing(routes())
}

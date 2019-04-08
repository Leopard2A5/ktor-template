package de.perschon.ktortemplate

import com.uchuhimo.konf.Config
import de.perschon.ktortemplate.config.KonfKtorConfig
import de.perschon.ktortemplate.config.Ktor
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    val config = Config { addSpec(Ktor) }
        .from.hocon.resource("application.conf")
        .from.env()

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
    routing {
        get("/") { call.respond("hi!") }
    }
}

package de.perschon.ktortemplate.config

import com.uchuhimo.konf.Config
import com.uchuhimo.konf.ConfigSpec

val topLevelConfigs = arrayOf(
    Ktor
)

object Ktor : ConfigSpec() {
    object Application : ConfigSpec() {
        val modules by required<Array<String>>()
    }
    object Deployment : ConfigSpec() {
        val port by required<Int>()
        val host by optional("0.0.0.0")
    }
}

fun loadConfig(): Config {
    val env = System.getenv("ENV") ?: "development"

    val conf = Config {
        topLevelConfigs.forEach { addSpec(it) }
    }.from.hocon.resource("application.conf")

    return when(env) {
        "development" -> conf
        "testing" -> conf.from.hocon.resource("application-testing.conf")
        "production" -> conf.from.hocon.resource("application-testing.conf")
        else -> throw IllegalArgumentException("Unsupported environment: $env")
    }.from.env()
}

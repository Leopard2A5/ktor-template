package de.perschon.ktortemplate.config

import com.uchuhimo.konf.ConfigSpec

object Ktor : ConfigSpec() {
    object Application : ConfigSpec() {
        val modules by required<Array<String>>()
    }
    object Deployment : ConfigSpec() {
        val port by required<Int>()
        val host by optional("0.0.0.0")
    }
}

package de.perschon.ktortemplate.config

import io.ktor.features.CallId
import io.ktor.http.HttpHeaders
import io.ktor.request.header
import java.util.*

val callId: CallId.Configuration.() -> Unit = {
    header(HttpHeaders.XRequestId)

    generate {
        UUID.randomUUID().toString()
    }
}

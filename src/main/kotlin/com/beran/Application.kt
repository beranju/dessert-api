package com.beran

import com.apurebase.kgraphql.GraphQL
import com.beran.di.mainModule
import com.beran.graphql.dessertSchema
import com.beran.services.DessertService
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.context.startKoin

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {

    // ** install koin
    startKoin {
        modules(mainModule)
    }

//    configureSecurity()
//    configureRouting()
    install(GraphQL) {
        val dessertService = DessertService()
        playground = true
        schema {
            dessertSchema(dessertService)
        }
    }
}

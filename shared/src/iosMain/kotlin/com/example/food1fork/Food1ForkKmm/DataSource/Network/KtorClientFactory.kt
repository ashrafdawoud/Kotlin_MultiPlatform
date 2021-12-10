package com.example.food1fork.Food1ForkKmm.DataSource.Network

import io.ktor.client.*
import io.ktor.client.engine.ios.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

actual class KtorClientFactory{
    actual fun build():HttpClient{
        return HttpClient(Ios){
            install(JsonFeature){
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                    }
                )
            }
        }
    }
}
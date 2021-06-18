package com.example.samplekmm.shared.remote.api

import com.example.samplekmm.shared.remote.response.XkcdResponse
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class XkcdApi {
    private val baseUrl = "https://xkcd.com"

    private val httpClient = HttpClient() {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    suspend fun fetchLatestComic() =
        httpClient.get<XkcdResponse>("$baseUrl/info.0.json")

}
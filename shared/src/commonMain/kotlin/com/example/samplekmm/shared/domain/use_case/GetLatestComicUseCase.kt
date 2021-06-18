package com.example.samplekmm.shared.domain.use_case

import com.example.samplekmm.shared.domain.model.ComicModel
import com.example.samplekmm.shared.remote.api.XkcdApi

class GetLatestComicUseCase(private val xkcdApi: XkcdApi) {
    suspend fun run() = xkcdApi.fetchLatestComic()
        .let { ComicModel(it.img, it.title) }
}
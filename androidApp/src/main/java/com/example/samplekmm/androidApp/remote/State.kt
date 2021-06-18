package com.example.samplekmm.androidApp.remote

import com.example.samplekmm.shared.domain.model.ComicModel

sealed class State {
    object Loading : State()
    class Success(val result: ComicModel) : State()
    object Error : State()
}
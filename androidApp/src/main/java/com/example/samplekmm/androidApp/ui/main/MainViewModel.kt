package com.example.samplekmm.androidApp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplekmm.androidApp.remote.State
import com.example.samplekmm.shared.domain.model.ComicModel
import com.example.samplekmm.shared.domain.use_case.GetLatestComicUseCase
import com.example.samplekmm.shared.remote.api.XkcdApi
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val getLatestComicUseCase = GetLatestComicUseCase(XkcdApi())
    val comic = MutableLiveData<State>()
    fun fetchComic() {
        viewModelScope.launch {
            comic.value = State.Loading
            runCatching { getLatestComicUseCase.run() }
                .onSuccess { comic.value = State.Success(it) }
                .onFailure {
                    comic.value = State.Error
                }
        }
    }
}
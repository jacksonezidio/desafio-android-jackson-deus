package com.example.marvel.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.data.repository.MainRepository
import com.example.marvel.ui.main.intent.MainIntent
import com.example.marvel.ui.main.viewstate.MainState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    val resultsIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            resultsIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchResult -> fetchResult()
                }
            }
        }
    }

    private fun fetchResult() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {
                MainState.Characters(repository.getResults())
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }
}
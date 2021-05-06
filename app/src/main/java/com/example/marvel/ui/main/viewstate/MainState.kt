package com.example.marvel.ui.main.viewstate

import com.example.marvel.data.model.MarvelResponse


sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    data class Characters(val response: MarvelResponse) : MainState()
    data class Error(val error: String?) : MainState()

}
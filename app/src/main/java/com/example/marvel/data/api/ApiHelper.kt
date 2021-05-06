package com.example.marvel.data.api

import com.example.marvel.data.model.MarvelResponse


interface ApiHelper {

    suspend fun getResults(): MarvelResponse

}
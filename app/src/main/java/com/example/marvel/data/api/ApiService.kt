package com.example.marvel.data.api

import com.example.marvel.data.model.MarvelResponse
import com.example.marvel.data.model.Results
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getResults(@Url url: String?): MarvelResponse

}
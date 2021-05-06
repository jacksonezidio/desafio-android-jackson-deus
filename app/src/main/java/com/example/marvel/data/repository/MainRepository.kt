package com.example.marvel.data.repository

import com.example.marvel.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getResults() = apiHelper.getResults()

}
package com.example.marvel.data.api

import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "http://gateway.marvel.com/"

    private fun getRetrofit() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    //GsonConverterFactory.create()

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

}
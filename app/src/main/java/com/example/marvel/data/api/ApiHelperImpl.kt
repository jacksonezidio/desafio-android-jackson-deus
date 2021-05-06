package com.example.marvel.data.api

import com.example.marvel.data.model.MarvelResponse
import com.example.marvel.data.model.Results
import com.example.marvel.util.Utils
import java.util.*

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getResults(): MarvelResponse {
        val publicKey = "824fbef03d88dc913b4919635e032bdd"
        val privateKey = "c1e82edb1d0ddc877783101c4bc23edea0b27a8f"
        val ts = "123" // todo: colocar timestamp
        val hash = Utils.md5(ts +privateKey +publicKey)

        val query = "/v1/public/characters?ts=${ts}&apikey=${publicKey}&hash=${hash}"

        return apiService.getResults(query)
    }
}
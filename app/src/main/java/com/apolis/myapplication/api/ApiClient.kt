package com.apolis.myapplication.api

import com.apolis.myapplication.model.RMCharacters
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * build retrofit object and returns an endpoint
 */
object ApiClient {
    const val BASE_URL = "https://rickandmortyapi.com/"
    val _endpoint: Endpoint by lazy {
        val client = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        client.create(Endpoint::class.java)
    }

    fun getEndpoint() = _endpoint
}

interface Endpoint {
    @GET("api/character/")
    fun getCharacters(): Call<RMCharacters>
}
package com.example.data.service.api

import com.example.data.service.response.CharacterResponse
import com.example.data.service.response.DataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApi {

    @GET("/v1/public/characters")
    fun getCharacters(): Call<DataResponse>

    @GET("/v1/public/characters/characterId")
    fun getCharacterById(@Path("characterId") characterId: String): Call<CharacterResponse>
}

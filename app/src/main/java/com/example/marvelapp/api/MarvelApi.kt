package com.example.marvelapp.api

import com.example.marvelapp.api.response.CharacterResponse
import com.example.marvelapp.api.response.DataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApi {

    @GET("/v1/public/characters")
    fun getCharacters(): Call<DataResponse>

    @GET("/v1/public/characters/characterId")
    fun getCharacterById(@Path("characterId") characterId: String): Call<CharacterResponse>
}

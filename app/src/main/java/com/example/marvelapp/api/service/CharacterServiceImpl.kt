package com.example.marvelapp.api.service

import com.example.marvelapp.api.MarvelApi
import com.example.marvelapp.api.util.Result
import com.example.marvelapp.api.util.transformToCharacterList
import com.example.marvelapp.entity.Character

class CharacterServiceImpl(private val api: ServiceGenerator) : CharacterService {

    override fun getCharacters(): Result<List<Character>> {
        try {
            val callResponse = api.createService(MarvelApi::class.java).getCharacters()
            val response = callResponse.execute()
            if (response.isSuccessful)
                response.body()?.let {
                    return Result.Success(it.transformToCharacterList())
                }
        } catch (e: Exception) {
            return Result.Failure(e)
        }
        return Result.Failure(Exception(NOT_FOUND))
    }

    companion object {
        private const val NOT_FOUND = "NOT_FOUND"
    }
}

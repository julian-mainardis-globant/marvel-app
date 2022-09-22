package com.example.marvelapp.api.service

import com.example.marvelapp.api.MarvelApi
import com.example.marvelapp.api.util.Result
import com.example.marvelapp.api.util.transformToLocalCharacter
import com.example.marvelapp.api.util.transformToLocalCharacterList
import com.example.marvelapp.entity.Character
import com.example.marvelapp.util.Constants

class CharacterServiceImpl(private val api: ServiceGenerator) : CharacterService {

    override fun getCharacters(): Result<List<Character>> {
        try {
            val callResponse = api.createService(MarvelApi::class.java).getCharacters()
            val response = callResponse.execute()
            if (response.isSuccessful)
                response.body()?.let {
                    return Result.Success(it.transformToLocalCharacterList())
                }
        } catch (e: Exception) {
            return Result.Failure(e)
        }
        return Result.Failure(Exception(Constants.NOT_FOUND))
    }

    override fun getCharacterById(characterId: String): Result<Character> {
        try {
            val callResponse = api.createService(MarvelApi::class.java).getCharacterById(characterId)
            val response = callResponse.execute()
            if (response.isSuccessful)
                response.body()?.let {
                    return Result.Success(it.transformToLocalCharacter())
                }
        } catch (e: Exception) {
            return Result.Failure(e)
        }
        return Result.Failure(Exception(Constants.NOT_FOUND))
    }
}

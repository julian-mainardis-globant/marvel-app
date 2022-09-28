package com.example.data.service

import com.example.data.service.api.MarvelApi
import com.example.data.service.util.Constants
import com.example.data.service.util.transformToLocalCharacter
import com.example.data.service.util.transformToLocalCharacterList
import com.example.domain.entity.Character
import com.example.domain.service.CharacterService
import com.example.domain.util.Result

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

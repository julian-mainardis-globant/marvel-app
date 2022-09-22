package com.example.marvelapp.usecase

import com.example.marvelapp.api.service.CharacterService
import com.example.marvelapp.api.util.Result
import com.example.marvelapp.database.MarvelRepository
import com.example.marvelapp.entity.Character

interface GetCharacterByIdUseCase {
    operator fun invoke(characterId: String): Result<Character>
}

class GetCharacterByIdUseCaseImpl(
    private val characterDetailService: CharacterService,
    private val database: MarvelRepository
) : GetCharacterByIdUseCase {

    override operator fun invoke(characterId: String): Result<Character> {
        return when (val serviceResult = characterDetailService.getCharacterById(characterId)) {
            is Result.Success -> {
                database.updateCharacterById(serviceResult.data)
                database.getCharacterById(serviceResult.data.id)
            }
            is Result.Failure -> {
                database.getCharacterById(characterId)
            }
        }
    }
}

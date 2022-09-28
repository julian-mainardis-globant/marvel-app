package com.example.domain.usecase

import com.example.domain.database.MarvelRepository
import com.example.domain.entity.Character
import com.example.domain.service.CharacterService
import com.example.domain.util.Result

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

package com.example.domain.usecase

import com.example.domain.database.MarvelRepository
import com.example.domain.entity.Character
import com.example.domain.service.CharacterService
import com.example.domain.util.Result

interface GetCharactersUseCase {
    operator fun invoke(): Result<List<Character>>
}

class GetCharactersUseCaseImpl(
    private val charactersService: CharacterService,
    private val database: MarvelRepository
) : GetCharactersUseCase {
    override operator fun invoke(): Result<List<Character>> {
        return when (val serviceResult = charactersService.getCharacters()) {
            is Result.Success -> {
                database.updateCharacters(serviceResult.data)
                database.getAllCharacters()
            }
            is Result.Failure -> {
                database.getAllCharacters()
            }
        }
    }
}

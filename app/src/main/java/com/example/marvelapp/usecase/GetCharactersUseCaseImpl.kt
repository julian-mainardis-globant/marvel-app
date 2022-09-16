package com.example.marvelapp.usecase

import com.example.marvelapp.api.service.CharacterService
import com.example.marvelapp.api.util.Result
import com.example.marvelapp.database.MarvelDataBase
import com.example.marvelapp.entity.Character

interface GetCharactersUseCase {
    operator fun invoke(): Result<List<Character>>
}

class GetCharactersUseCaseImpl(
    private val charactersService: CharacterService,
    private val database: MarvelDataBase
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

package com.example.marvelapp.usecase

import com.example.marvelapp.api.service.CharacterService
import com.example.marvelapp.api.util.Result
import com.example.marvelapp.entity.Character

interface GetCharactersUseCase {
    operator fun invoke(): Result<List<Character>>
}

class GetCharactersUseCaseImpl(private val charactersService: CharacterService) : GetCharactersUseCase {
    override operator fun invoke(): Result<List<Character>> = charactersService.getCharacters()
}

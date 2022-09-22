package com.example.marvelapp.mvvm.model

import com.example.marvelapp.api.util.Result
import com.example.marvelapp.entity.Character
import com.example.marvelapp.usecase.GetCharacterByIdUseCase

class CharacterDetailFragmentModel(private val getCharacterByIdUseCase: GetCharacterByIdUseCase) {
    fun getCharacterById(characterId: String): Result<Character> = getCharacterByIdUseCase(characterId)
}

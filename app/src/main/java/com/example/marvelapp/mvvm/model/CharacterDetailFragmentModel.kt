package com.example.marvelapp.mvvm.model

import com.example.domain.entity.Character
import com.example.domain.usecase.GetCharacterByIdUseCase
import com.example.domain.util.Result

class CharacterDetailFragmentModel(private val getCharacterByIdUseCase: GetCharacterByIdUseCase) {
    fun getCharacterById(characterId: String): Result<Character> = getCharacterByIdUseCase(characterId)
}

package com.example.marvelapp.mvvm.model

import com.example.domain.entity.Character
import com.example.domain.usecase.GetCharactersUseCase
import com.example.domain.util.Result

class CharacterListModel(private val getCharactersUseCase: GetCharactersUseCase) {
    fun getCharacters(): Result<List<Character>> = getCharactersUseCase()
}

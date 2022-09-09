package com.example.marvelapp.mvvm.model

import com.example.marvelapp.api.util.Result
import com.example.marvelapp.entity.Character
import com.example.marvelapp.usecase.GetCharactersUseCase

class CharacterListModel(private val getCharactersUseCase: GetCharactersUseCase) {
    fun getCharacters(): Result<List<Character>> = getCharactersUseCase()
}

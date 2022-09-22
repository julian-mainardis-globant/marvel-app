package com.example.marvelapp.api.service

import com.example.marvelapp.api.util.Result
import com.example.marvelapp.entity.Character

interface CharacterService {
    fun getCharacters(): Result<List<Character>>
    fun getCharacterById(characterId: String): Result<Character>
}

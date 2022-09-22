package com.example.marvelapp.database

import com.example.marvelapp.api.util.Result
import com.example.marvelapp.entity.Character

interface MarvelRepository {
    fun getAllCharacters(): Result<List<Character>>
    fun updateCharacters(characters: List<Character>)
    fun getCharacterById(characterId: String): Result<Character>
    fun updateCharacterById(character: Character)
}

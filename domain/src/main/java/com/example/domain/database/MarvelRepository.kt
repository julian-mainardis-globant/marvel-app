package com.example.domain.database

import com.example.domain.entity.Character
import com.example.domain.util.Result

interface MarvelRepository {
    fun getAllCharacters(): Result<List<Character>>
    fun updateCharacters(characters: List<Character>)
    fun getCharacterById(characterId: String): Result<Character>
    fun updateCharacterById(character: Character)
}

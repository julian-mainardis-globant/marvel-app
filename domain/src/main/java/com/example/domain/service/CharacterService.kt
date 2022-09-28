package com.example.domain.service

import com.example.domain.entity.Character
import com.example.domain.util.Result

interface CharacterService {
    fun getCharacters(): Result<List<Character>>
    fun getCharacterById(characterId: String): Result<Character>
}

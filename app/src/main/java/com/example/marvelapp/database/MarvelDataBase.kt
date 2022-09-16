package com.example.marvelapp.database

import com.example.marvelapp.api.util.Result
import com.example.marvelapp.entity.Character

interface MarvelDataBase {
    fun getAllCharacters(): Result<List<Character>>
    fun updateCharacters(characters: List<Character>)
}

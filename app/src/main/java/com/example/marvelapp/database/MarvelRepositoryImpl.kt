package com.example.marvelapp.database

import com.example.marvelapp.api.util.Result
import com.example.marvelapp.api.util.transformToCharacterEntity
import com.example.marvelapp.api.util.transformToLocalCharacter
import com.example.marvelapp.api.util.transformToLocalCharacterList
import com.example.marvelapp.entity.Character
import com.example.marvelapp.util.Constants

class MarvelRepositoryImpl(private val marvelDao: MarvelDao) : MarvelRepository {

    override fun getAllCharacters(): Result<List<Character>> =
        marvelDao.getAllCharacters().let {
            if (it.isNotEmpty()) {
                Result.Success(it.transformToLocalCharacterList())
            } else {
                Result.Failure(Exception(Constants.NOT_FOUND))
            }
        }

    override fun updateCharacters(characters: List<Character>) {
        characters.forEach {
            marvelDao.insertCharacter(it.transformToCharacterEntity())
        }
    }

    override fun getCharacterById(characterId: String): Result<Character> =
        marvelDao.getCharacterById(characterId).let {
            if (it.isNotEmpty()) {
                Result.Success(it.first().transformToLocalCharacter())
            } else {
                Result.Failure(Exception(Constants.NOT_FOUND))
            }
        }

    override fun updateCharacterById(character: Character) {
        marvelDao.insertCharacter(character.transformToCharacterEntity())
    }
}

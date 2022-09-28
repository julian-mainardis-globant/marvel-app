package com.example.data.database

import com.example.data.service.util.Constants
import com.example.data.service.util.transformToCharacterEntity
import com.example.data.service.util.transformToLocalCharacter
import com.example.data.service.util.transformToLocalCharacterList
import com.example.domain.database.MarvelRepository
import com.example.domain.entity.Character
import com.example.domain.util.Result

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

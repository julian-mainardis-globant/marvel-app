package com.example.marvelapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvelapp.api.util.Result
import com.example.marvelapp.api.util.transformToCharacterEntity
import com.example.marvelapp.api.util.transformToLocalCharacterList
import com.example.marvelapp.database.entity.CharacterEntity
import com.example.marvelapp.entity.Character

@Database(
    entities = [CharacterEntity::class],
    version = 1
)
abstract class MarvelDataBaseImpl : RoomDatabase(), MarvelDataBase {

    abstract fun marvelDao(): MarvelDao

    override fun getAllCharacters(): Result<List<Character>> =
        marvelDao().getAllCharacters().let {
            if (it.isNotEmpty()) {
                Result.Success(it.transformToLocalCharacterList())
            } else {
                Result.Failure(Exception(NOT_FOUND))
            }
        }

    override fun updateCharacters(characters: List<Character>) {
        characters.forEach {
            marvelDao().insertCharacter(it.transformToCharacterEntity())
        }
    }

    companion object {
        private const val NOT_FOUND = "NOT_FOUND"
    }
}

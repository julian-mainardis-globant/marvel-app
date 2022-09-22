package com.example.marvelapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelapp.database.entity.CharacterEntity

@Dao
interface MarvelDao {
    @Query("SELECT * FROM character_table")
    fun getAllCharacters(): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: CharacterEntity)

    @Query("SELECT * FROM character_table WHERE id = :characterId")
    fun getCharacterById(characterId: String): List<CharacterEntity>
}

package com.example.marvelapp.api.util

import com.example.marvelapp.api.response.DataResponse
import com.example.marvelapp.database.entity.CharacterEntity
import com.example.marvelapp.entity.Character

fun DataResponse.transformToLocalCharacterList(): List<Character> {
    val characterList = mutableListOf<Character>()
    data.results.forEach {
        characterList.add(
            Character(
                it.id,
                it.name,
                it.description
            )
        )
    }
    return characterList
}

fun CharacterEntity.transformToLocalCharacter(): Character {
    return Character(
        this.id,
        this.name,
        this.description
    )
}

fun Character.transformToCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        this.id,
        this.name,
        this.description
    )
}

fun List<CharacterEntity>.transformToLocalCharacterList(): List<Character> =
    this.map { it.transformToLocalCharacter() }

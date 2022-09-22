package com.example.marvelapp.api.util

import com.example.marvelapp.api.response.CharacterResponse
import com.example.marvelapp.api.response.DataResponse
import com.example.marvelapp.api.response.ImageResponse
import com.example.marvelapp.database.entity.CharacterEntity
import com.example.marvelapp.entity.Character
import com.example.marvelapp.util.Constants

fun DataResponse.transformToLocalCharacterList(): List<Character> {
    val characterList = mutableListOf<Character>()
    data.results.forEach {
        characterList.add(
            Character(
                it.id,
                it.name,
                it.description,
                it.image.getPathAndExtension()
            )
        )
    }
    return characterList
}

fun CharacterResponse.transformToLocalCharacter() =
    Character(
        this.id,
        this.name,
        this.description,
        this.image.getPathAndExtension()
    )

fun CharacterEntity.transformToLocalCharacter() =
    Character(
        this.id,
        this.name,
        this.description,
        this.imageURL
    )

fun Character.transformToCharacterEntity() =
    CharacterEntity(
        this.id,
        this.name,
        this.description,
        this.imageURL
    )

fun ImageResponse.getPathAndExtension() = "${this.path}${Constants.DOT}${this.extension}"

fun List<CharacterEntity>.transformToLocalCharacterList(): List<Character> =
    this.map { it.transformToLocalCharacter() }

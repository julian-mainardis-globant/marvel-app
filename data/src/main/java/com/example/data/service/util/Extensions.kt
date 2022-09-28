package com.example.data.service.util

import com.example.data.entity.CharacterEntity
import com.example.data.service.response.CharacterResponse
import com.example.data.service.response.DataResponse
import com.example.data.service.response.ImageResponse
import com.example.domain.entity.Character

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

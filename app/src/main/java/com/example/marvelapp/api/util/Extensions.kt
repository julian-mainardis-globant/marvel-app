package com.example.marvelapp.api.util

import com.example.marvelapp.api.response.DataResponse
import com.example.marvelapp.entity.Character

fun DataResponse.transformToCharacterList() : List<Character> {
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

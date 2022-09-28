package com.example.data.mapper

import com.example.data.service.util.transformToCharacterEntity
import com.example.domain.entity.Character
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocalCharacterMapperTest {

    private lateinit var character: Character

    @Before
    fun init() {
        character = Character(id = ID, name = NAME, description = DESCRIPTION, imageURL = IMAGE_URL)
    }

    @Test
    fun `transform local character to entity character`() {
        val localCharacterTransformed = character.transformToCharacterEntity()
        assertEquals(localCharacterTransformed.id, character.id)
        assertEquals(localCharacterTransformed.name, character.name)
        assertEquals(localCharacterTransformed.description, character.description)
        assertEquals(localCharacterTransformed.imageURL, character.imageURL)
    }

    companion object {
        private const val ID = "1"
        private const val NAME = "Pepe"
        private const val DESCRIPTION = "Pepe es un buen samaritano"
        private const val IMAGE_URL = "http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec.jpg"
    }
}

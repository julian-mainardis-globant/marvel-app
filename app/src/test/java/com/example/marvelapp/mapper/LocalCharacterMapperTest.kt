package com.example.marvelapp.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelapp.api.util.transformToCharacterEntity
import com.example.marvelapp.entity.Character
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocalCharacterMapperTest {

    private lateinit var character: Character

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        character = Character(id = ID, name = NAME, description = DESCRIPTION)
    }

    @Test
    fun `transform local character to entity character`() {
        val localCharacterTransformed = character.transformToCharacterEntity()
        assertEquals(localCharacterTransformed.id, character.id)
        assertEquals(localCharacterTransformed.name, character.name)
        assertEquals(localCharacterTransformed.description, character.description)
    }

    companion object {
        const val ID = "1"
        const val NAME = "Pepe"
        const val DESCRIPTION = "Pepe es un buen samaritano"
    }
}

package com.example.marvelapp.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelapp.api.util.transformToLocalCharacter
import com.example.marvelapp.api.util.transformToLocalCharacterList
import com.example.marvelapp.database.entity.CharacterEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EntityCharacterMapperTest {

    private lateinit var characterEntity: CharacterEntity
    private lateinit var characterEntityList: List<CharacterEntity>

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        characterEntity = CharacterEntity(id = ID, name = NAME, description = DESCRIPTION)
        characterEntityList = mutableListOf(
            CharacterEntity(
                id = DataResponseMapperTest.ID,
                name = DataResponseMapperTest.NAME,
                description = DataResponseMapperTest.DESCRIPTION
            )
        )
    }

    @Test
    fun `transform entity character to local character`() {
        val characterEntityTransformed = characterEntity.transformToLocalCharacter()
        assertEquals(characterEntityTransformed.id, characterEntity.id)
        assertEquals(characterEntityTransformed.name, characterEntity.name)
        assertEquals(characterEntityTransformed.description, characterEntity.description)
    }

    @Test
    fun `transform list of entity character to list of local character`() {
        val characterEntityListTransformed = characterEntityList.transformToLocalCharacterList()
        assertEquals(characterEntityListTransformed[0].id, characterEntityList[0].id)
        assertEquals(characterEntityListTransformed[0].name, characterEntityList[0].name)
        assertEquals(characterEntityListTransformed[0].description, characterEntityList[0].description)
    }

    companion object {
        const val ID = "1"
        const val NAME = "Pepe"
        const val DESCRIPTION = "Pepe es un buen samaritano"
    }
}

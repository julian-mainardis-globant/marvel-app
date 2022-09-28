package com.example.data.mapper

import com.example.data.entity.CharacterEntity
import com.example.data.service.util.transformToLocalCharacter
import com.example.data.service.util.transformToLocalCharacterList
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EntityCharacterMapperTest {

    private lateinit var characterEntity: CharacterEntity
    private lateinit var characterEntityList: List<CharacterEntity>

    @Before
    fun init() {
        characterEntity =
            CharacterEntity(id = ID, name = NAME, description = DESCRIPTION, imageURL = IMAGE_URL)
        characterEntityList = mutableListOf(
            CharacterEntity(
                id = ID,
                name = NAME,
                description = DESCRIPTION,
                imageURL = IMAGE_URL
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
        private const val ID = "1"
        private const val NAME = "Pepe"
        private const val DESCRIPTION = "Pepe es un buen samaritano"
        private const val IMAGE_URL = "http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec.jpg"
    }
}

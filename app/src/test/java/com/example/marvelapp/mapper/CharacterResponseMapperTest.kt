package com.example.marvelapp.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelapp.api.response.CharacterResponse
import com.example.marvelapp.api.response.ImageResponse
import com.example.marvelapp.api.util.getPathAndExtension
import com.example.marvelapp.api.util.transformToLocalCharacter
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterResponseMapperTest {

    private lateinit var characterResponse: CharacterResponse
    private lateinit var imageResponse: ImageResponse

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        imageResponse = ImageResponse(PATH, EXTENSION)
        characterResponse = CharacterResponse(id = ID, name = NAME, description = DESCRIPTION, image = imageResponse)
    }

    @Test
    fun `transform character response to local character`() {
        val response = characterResponse.transformToLocalCharacter()
        Assert.assertEquals(response.id, characterResponse.id)
        Assert.assertEquals(response.name, characterResponse.name)
        Assert.assertEquals(response.description, characterResponse.description)
        Assert.assertEquals(response.imageURL, characterResponse.image.getPathAndExtension())
    }

    companion object {
        private const val ID = "1"
        private const val NAME = "Pepe"
        private const val DESCRIPTION = "Pepe es un buen samaritano"
        private const val PATH = "http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec"
        private const val EXTENSION = "jpg"
    }
}

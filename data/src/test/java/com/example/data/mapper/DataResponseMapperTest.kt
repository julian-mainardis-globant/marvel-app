package com.example.data.mapper

import com.example.data.service.response.CharacterResponse
import com.example.data.service.response.DataResponse
import com.example.data.service.response.ImageResponse
import com.example.data.service.response.ResultResponse
import com.example.data.service.util.getPathAndExtension
import com.example.data.service.util.transformToLocalCharacterList
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataResponseMapperTest {

    private lateinit var dataResponse: DataResponse
    private lateinit var resultResponse: ResultResponse
    private lateinit var imageResponse: ImageResponse

    @Before
    fun init() {
        imageResponse = ImageResponse(PATH, EXTENSION)
        resultResponse =
            ResultResponse(mutableListOf(CharacterResponse(id = ID, name = NAME, description = DESCRIPTION, image = imageResponse)))
        dataResponse = DataResponse(resultResponse)
    }

    @Test
    fun `transform data response to local character list`() {
        val response = dataResponse.transformToLocalCharacterList()
        assertEquals(response[0].id, resultResponse.results[0].id)
        assertEquals(response[0].name, resultResponse.results[0].name)
        assertEquals(response[0].description, resultResponse.results[0].description)
        assertEquals(response[0].imageURL, resultResponse.results[0].image.getPathAndExtension())
    }

    companion object {
        const val ID = "1"
        const val NAME = "Pepe"
        const val DESCRIPTION = "Pepe es un buen samaritano"
        const val PATH = "http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec"
        const val EXTENSION = "jpg"
    }
}


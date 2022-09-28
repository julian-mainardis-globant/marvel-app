package com.example.data.mapper

import com.example.data.service.response.ImageResponse
import com.example.data.service.util.getPathAndExtension
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImageResponseMapperTest {

    private lateinit var imageResponse: ImageResponse

    @Before
    fun init() {
        imageResponse = ImageResponse(path = PATH, extension = EXTENSION)
    }

    @Test
    fun `transform thumbnail objet to a string URL for Character`() {
        val stringURL = imageResponse.getPathAndExtension()
        assertEquals(stringURL, URL)
    }

    companion object {
        private const val PATH = "http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec"
        private const val EXTENSION = "jpg"
        private const val URL = "$PATH.$EXTENSION"
    }
}

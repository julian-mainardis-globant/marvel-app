package com.example.marvelapp.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelapp.api.response.ImageResponse
import com.example.marvelapp.api.util.getPathAndExtension
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ImageResponseMapperTest {

    private lateinit var imageResponse: ImageResponse

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

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
        const val PATH = "http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec"
        const val EXTENSION = "jpg"
        const val URL = "$PATH.$EXTENSION"
    }
}

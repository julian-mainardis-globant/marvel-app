package com.example.domain

import com.example.domain.database.MarvelRepository
import com.example.domain.entity.Character
import com.example.domain.service.CharacterService
import com.example.domain.usecase.GetCharacterByIdUseCase
import com.example.domain.usecase.GetCharacterByIdUseCaseImpl
import com.example.domain.util.Result
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetCharacterByIdUseCaseTest {

    private lateinit var getCharacterByIdUseCase: GetCharacterByIdUseCase
    private val characterDetailService: CharacterService = mock()
    private val database: MarvelRepository = mock()
    private lateinit var character: Character

    @Before
    fun init() {
        getCharacterByIdUseCase = GetCharacterByIdUseCaseImpl(characterDetailService, database)
        character = Character(id = VALID_ID, name = NAME, description = DESCRIPTION, imageURL = IMAGE_URL)
    }

    @Test
    fun `when the service return a success result`() {
        whenever(characterDetailService.getCharacterById(VALID_ID)).thenReturn(Result.Success(character))
        whenever(database.getCharacterById(VALID_ID)).thenReturn(Result.Success(character))

        val result = getCharacterByIdUseCase(character.id)

        verify(database).updateCharacterById(character)
        verify(database).getCharacterById(character.id)

        assertEquals(character, (result as Result.Success).data)
    }

    @Test
    fun `when the service return a failure result and the database is empty`() {
        whenever(characterDetailService.getCharacterById(VALID_ID)).thenReturn(Result.Failure(Exception(NOT_FOUND)))
        whenever(database.getCharacterById(VALID_ID)).thenReturn(Result.Failure(Exception(NOT_FOUND)))

        val result = getCharacterByIdUseCase(character.id)

        verify(database).getCharacterById(character.id)

        assertEquals(NOT_FOUND, (result as Result.Failure).exception.message)
    }

    @Test
    fun `when the service return a failure result and the database isn't empty`() {
        whenever(characterDetailService.getCharacterById(WRONG_ID)).thenReturn(Result.Failure(Exception(NOT_FOUND)))
        whenever(database.getCharacterById(WRONG_ID)).thenReturn(Result.Success(character))

        val result = getCharacterByIdUseCase(WRONG_ID)

        verify(database).getCharacterById(WRONG_ID)

        assertEquals(character, (result as Result.Success).data)
    }

    companion object {
        private const val NOT_FOUND = "NOT_FOUND"
        private const val VALID_ID = "1011334"
        private const val WRONG_ID = "1"
        private const val NAME = "Pepe"
        private const val DESCRIPTION = "Pepe es un buen samaritano"
        private const val IMAGE_URL = "http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec.jpg"
    }
}

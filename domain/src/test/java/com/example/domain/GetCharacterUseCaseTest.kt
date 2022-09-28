package com.example.domain

import com.example.domain.database.MarvelRepository
import com.example.domain.entity.Character
import com.example.domain.service.CharacterService
import com.example.domain.usecase.GetCharactersUseCase
import com.example.domain.usecase.GetCharactersUseCaseImpl
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
class GetCharacterUseCaseTest {

    private lateinit var getCharactersUseCase: GetCharactersUseCase
    private val characterService: CharacterService = mock()
    private val database: MarvelRepository = mock()
    private val characterList: List<Character> = mock()

    @Before
    fun init() {
        getCharactersUseCase = GetCharactersUseCaseImpl(characterService, database)
    }

    @Test
    fun `when the service return a success result`() {
        whenever(characterService.getCharacters()).thenReturn(Result.Success(characterList))
        whenever(database.getAllCharacters()).thenReturn(Result.Success(characterList))

        val result = getCharactersUseCase()

        verify(database).updateCharacters(characterList)
        verify(database).getAllCharacters()

        assertEquals(characterList, (result as Result.Success).data)
    }

    @Test
    fun `when the service return a failure result and the database is empty`() {
        whenever(characterService.getCharacters()).thenReturn(Result.Failure(Exception(NOT_FOUND)))
        whenever(database.getAllCharacters()).thenReturn(Result.Failure(Exception(NOT_FOUND)))

        val result = getCharactersUseCase()

        verify(database).getAllCharacters()

        assertEquals(NOT_FOUND, (result as Result.Failure).exception.message)
    }

    @Test
    fun `when the service return a failure result and the database isn't empty`() {
        whenever(characterService.getCharacters()).thenReturn(Result.Failure(Exception(NOT_FOUND)))
        whenever(database.getAllCharacters()).thenReturn(Result.Success(characterList))

        val result = getCharactersUseCase()

        verify(database).getAllCharacters()

        assertEquals(characterList, (result as Result.Success).data)
    }

    companion object {
        private const val NOT_FOUND = "NOT_FOUND"
    }
}

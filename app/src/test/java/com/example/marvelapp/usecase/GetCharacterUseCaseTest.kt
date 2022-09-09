package com.example.marvelapp.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelapp.api.service.CharacterService
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class GetCharacterUseCaseTest {

    private lateinit var getCharactersUseCase: GetCharactersUseCase
    private val characterService: CharacterService = mock()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        getCharactersUseCase = GetCharactersUseCaseImpl(characterService)
    }

    @Test
    fun invokeTest() {
        getCharactersUseCase.invoke()
        verify(characterService).getCharacters()
    }
}

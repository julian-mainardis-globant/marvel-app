package com.example.marvelapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.entity.Character
import com.example.domain.usecase.GetCharacterByIdUseCase
import com.example.domain.util.Result
import com.example.marvelapp.mvvm.model.CharacterDetailFragmentModel
import com.example.marvelapp.mvvm.viewmodel.CharacterDetailFragmentViewModel
import com.example.marvelapp.testObserver
import com.example.marvelapp.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterDetailFragmentViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: CharacterDetailFragmentViewModel
    private lateinit var model: CharacterDetailFragmentModel
    private val getCharactersByIdUseCase: GetCharacterByIdUseCase = mock()
    private lateinit var character: Character
    private val exception: Exception = mock()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        model = CharacterDetailFragmentModel(getCharactersByIdUseCase)
        viewModel = CharacterDetailFragmentViewModel(model)
        character = Character(id = VALID_ID, name = NAME, description = DESCRIPTION, imageURL = IMAGE_URL)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when getCharacterById return success result`() {
        val liveData = viewModel.characterDetailState.testObserver()

        whenever(getCharactersByIdUseCase(VALID_ID)).thenReturn(Result.Success(character))

        runBlocking {
            viewModel.getCharacterById(character.id).join()
        }

        assertEquals(CharacterDetailFragmentViewModel.CharacterDetailState.RESPONSE_LOADING, liveData.observedValues[0]?.state)
        assertEquals(CharacterDetailFragmentViewModel.CharacterDetailState.RESPONSE_SUCCESS, liveData.observedValues[1]?.state)
    }

    @Test
    fun `when getCharacterById return failure result with general error`() {
        val liveData = viewModel.characterDetailState.testObserver()

        whenever(getCharactersByIdUseCase(VALID_ID)).thenReturn(Result.Failure(exception = exception))

        runBlocking {
            viewModel.getCharacterById(character.id).join()
        }

        assertEquals(CharacterDetailFragmentViewModel.CharacterDetailState.RESPONSE_LOADING, liveData.observedValues[0]?.state)
        assertEquals(CharacterDetailFragmentViewModel.CharacterDetailState.RESPONSE_ERROR, liveData.observedValues[1]?.state)
    }

    @Test
    fun `when getCharacterById return failure result with not found error`() {
        val liveData = viewModel.characterDetailState.testObserver()

        whenever(getCharactersByIdUseCase(WRONG_ID)).thenReturn(Result.Failure(Exception(Constants.NOT_FOUND)))

        runBlocking {
            viewModel.getCharacterById(WRONG_ID).join()
        }

        assertEquals(CharacterDetailFragmentViewModel.CharacterDetailState.RESPONSE_LOADING, liveData.observedValues[0]?.state)
        assertEquals(CharacterDetailFragmentViewModel.CharacterDetailState.RESPONSE_ERROR_NOT_FOUND, liveData.observedValues[1]?.state)
    }

    companion object {
        private const val VALID_ID = "1011334"
        private const val WRONG_ID = "1"
        private const val NAME = "Pepe"
        private const val DESCRIPTION = "Pepe es un buen samaritano"
        private const val IMAGE_URL = "http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec.jpg"
    }
}

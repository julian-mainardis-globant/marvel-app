package com.example.marvelapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.entity.Character
import com.example.domain.usecase.GetCharactersUseCase
import com.example.domain.util.Result
import com.example.marvelapp.mvvm.model.CharacterListModel
import com.example.marvelapp.mvvm.viewmodel.CharacterListViewModel
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
class CharacterListViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: CharacterListViewModel
    private lateinit var model: CharacterListModel
    private val getCharactersUseCase: GetCharactersUseCase = mock()
    private val listOfCharacters: List<Character> = mock()
    private val exception: Exception = mock()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        model = CharacterListModel(getCharactersUseCase)
        viewModel = CharacterListViewModel(model)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when getCharacters return success result`() {
        val liveData = viewModel.characterState.testObserver()

        whenever(getCharactersUseCase()).thenReturn(Result.Success(listOfCharacters))

        runBlocking {
            viewModel.getCharacters().join()
        }

        assertEquals(CharacterListViewModel.CharacterState.RESPONSE_LOADING, liveData.observedValues[0]?.state)
        assertEquals(CharacterListViewModel.CharacterState.RESPONSE_SUCCESS, liveData.observedValues[1]?.state)
    }

    @Test
    fun `when getCharacters return failure result with general error`() {
        val liveData = viewModel.characterState.testObserver()

        whenever(getCharactersUseCase()).thenReturn(Result.Failure(exception = exception))

        runBlocking {
            viewModel.getCharacters().join()
        }

        assertEquals(CharacterListViewModel.CharacterState.RESPONSE_LOADING, liveData.observedValues[0]?.state)
        assertEquals(CharacterListViewModel.CharacterState.RESPONSE_ERROR, liveData.observedValues[1]?.state)
    }

    @Test
    fun `when getCharacters return failure result with not found error`() {
        val liveData = viewModel.characterState.testObserver()

        whenever(getCharactersUseCase()).thenReturn(Result.Failure(Exception(Constants.NOT_FOUND)))

        runBlocking {
            viewModel.getCharacters().join()
        }

        assertEquals(CharacterListViewModel.CharacterState.RESPONSE_LOADING, liveData.observedValues[0]?.state)
        assertEquals(CharacterListViewModel.CharacterState.RESPONSE_ERROR_NOT_FOUND, liveData.observedValues[1]?.state)
    }
}

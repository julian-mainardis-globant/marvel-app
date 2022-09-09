package com.example.marvelapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelapp.mvvm.viewmodel.MainViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    private lateinit var mainActivityViewModel: MainViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        mainActivityViewModel = MainViewModel()
    }

    @Test
    fun onButtonPressedTest() {
        mainActivityViewModel.onButtonPressed()
        Assert.assertEquals(mainActivityViewModel.buttonState.value?.state, MainViewModel.ButtonState.PRESSED)
    }
}

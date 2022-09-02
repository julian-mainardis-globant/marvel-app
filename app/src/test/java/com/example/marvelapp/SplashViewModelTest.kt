package com.example.marvelapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelapp.mvvm.viewmodel.SplashActivityViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {

    private lateinit var splashActivityViewModel: SplashActivityViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        splashActivityViewModel = SplashActivityViewModel()
    }

    @Test
    fun startAnimTest() {
        splashActivityViewModel.startAnim()
        Assert.assertEquals(splashActivityViewModel.animState.value?.state, SplashActivityViewModel.SplashState.INITIAL)
    }

    @Test
    fun endedAnimTest() {
        splashActivityViewModel.endedAnim()
        Assert.assertEquals(splashActivityViewModel.animState.value?.state, SplashActivityViewModel.SplashState.ENDED)
    }
}

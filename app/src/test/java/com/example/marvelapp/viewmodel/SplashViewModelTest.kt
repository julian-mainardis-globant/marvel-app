package com.example.marvelapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelapp.mvvm.viewmodel.SplashViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {

    private lateinit var splashActivityViewModel: SplashViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        splashActivityViewModel = SplashViewModel()
    }

    @Test
    fun startAnimTest() {
        splashActivityViewModel.startAnim()
        Assert.assertEquals(splashActivityViewModel.animState.value?.state, SplashViewModel.SplashState.INITIAL)
    }

    @Test
    fun endedAnimTest() {
        splashActivityViewModel.endedAnim()
        Assert.assertEquals(splashActivityViewModel.animState.value?.state, SplashViewModel.SplashState.ENDED)
    }
}

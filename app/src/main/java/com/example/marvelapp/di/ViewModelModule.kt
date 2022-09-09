package com.example.marvelapp.di

import com.example.marvelapp.mvvm.viewmodel.CharacterListViewModel
import com.example.marvelapp.mvvm.viewmodel.MainViewModel
import com.example.marvelapp.mvvm.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val viewModelModule = module {
        viewModel { SplashViewModel() }
        viewModel { MainViewModel() }
        viewModel { CharacterListViewModel(get()) }
    }
}

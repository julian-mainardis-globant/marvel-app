package com.example.marvelapp.di

import com.example.marvelapp.usecase.GetCharacterByIdUseCase
import com.example.marvelapp.usecase.GetCharacterByIdUseCaseImpl
import com.example.marvelapp.usecase.GetCharactersUseCase
import com.example.marvelapp.usecase.GetCharactersUseCaseImpl
import org.koin.dsl.module

object UseCaseModule {
    val useCaseModule = module {
        factory<GetCharactersUseCase> { GetCharactersUseCaseImpl(get(), get()) }
        factory<GetCharacterByIdUseCase> { GetCharacterByIdUseCaseImpl(get(), get()) }
    }
}

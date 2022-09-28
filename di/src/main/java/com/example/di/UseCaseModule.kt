package com.example.di

import com.example.domain.usecase.GetCharacterByIdUseCase
import com.example.domain.usecase.GetCharacterByIdUseCaseImpl
import com.example.domain.usecase.GetCharactersUseCase
import com.example.domain.usecase.GetCharactersUseCaseImpl
import org.koin.dsl.module

object UseCaseModule {
    val useCaseModule = module {
        factory<GetCharactersUseCase> { GetCharactersUseCaseImpl(get(), get()) }
        factory<GetCharacterByIdUseCase> { GetCharacterByIdUseCaseImpl(get(), get()) }
    }
}

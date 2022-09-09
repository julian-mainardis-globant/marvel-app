package com.example.marvelapp.di

import com.example.marvelapp.api.service.CharacterService
import com.example.marvelapp.api.service.CharacterServiceImpl
import org.koin.dsl.module

object ServiceModule {
    val serviceModule = module {
        factory<CharacterService> { CharacterServiceImpl(get()) }
    }
}

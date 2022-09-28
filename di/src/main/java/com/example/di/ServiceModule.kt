package com.example.di

import com.example.data.service.CharacterServiceImpl
import com.example.domain.service.CharacterService
import org.koin.dsl.module

object ServiceModule {
    val serviceModule = module {
        factory<CharacterService> { CharacterServiceImpl(get()) }
    }
}

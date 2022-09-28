package com.example.di

import com.example.data.database.MarvelRepositoryImpl
import com.example.domain.database.MarvelRepository
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        factory<MarvelRepository> { MarvelRepositoryImpl(get()) }
    }
}

package com.example.marvelapp.di

import com.example.marvelapp.database.MarvelRepository
import com.example.marvelapp.database.MarvelRepositoryImpl
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        factory<MarvelRepository> { MarvelRepositoryImpl(get()) }
    }
}

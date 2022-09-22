package com.example.marvelapp.di

import androidx.room.Room
import com.example.marvelapp.database.MarvelDataBase
import org.koin.dsl.module

object DataBaseModule {
    private const val DATA_BASE_NAME = "MarvelRepository"

    val dataBaseModule = module {
        single { Room.databaseBuilder(get(), MarvelDataBase::class.java, DATA_BASE_NAME).build() }
        single { get<MarvelDataBase>().marvelDao() }
    }
}

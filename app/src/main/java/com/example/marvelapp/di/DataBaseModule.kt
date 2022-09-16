package com.example.marvelapp.di

import androidx.room.Room
import com.example.marvelapp.database.MarvelDataBase
import com.example.marvelapp.database.MarvelDataBaseImpl
import org.koin.dsl.module

object DataBaseModule {
    private const val DATA_BASE_NAME = "MarvelDataBase"

    val dataBaseModule = module {
        single<MarvelDataBase> { Room.databaseBuilder(get(), MarvelDataBaseImpl::class.java, DATA_BASE_NAME).build() }
        single { get<MarvelDataBaseImpl>().marvelDao() }
    }
}

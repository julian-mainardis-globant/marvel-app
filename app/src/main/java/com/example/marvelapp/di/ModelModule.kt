package com.example.marvelapp.di

import com.example.marvelapp.mvvm.model.CharacterListModel
import org.koin.dsl.module

object ModelModule {
    val modelModule = module {
        factory { CharacterListModel(get()) }
    }
}

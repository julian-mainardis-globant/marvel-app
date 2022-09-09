package com.example.marvelapp.di

import com.example.marvelapp.api.service.ServiceGenerator
import org.koin.dsl.module

object ApiModule {
    val apiModule = module {
        factory { ServiceGenerator() }
    }
}

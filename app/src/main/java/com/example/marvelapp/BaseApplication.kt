package com.example.marvelapp

import android.app.Application
import com.example.marvelapp.di.ApiModule.apiModule
import com.example.marvelapp.di.ModelModule.modelModule
import com.example.marvelapp.di.ServiceModule.serviceModule
import com.example.marvelapp.di.UseCaseModule.useCaseModule
import com.example.marvelapp.di.ViewModelModule.viewModelModule
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class BaseApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    viewModelModule,
                    useCaseModule,
                    serviceModule,
                    modelModule,
                    apiModule
                )
            )
        }
    }
}

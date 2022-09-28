package com.example.marvelapp

import android.app.Application
import com.example.di.ApiModule.apiModule
import com.example.di.DataBaseModule.dataBaseModule
import com.example.di.RepositoryModule.repositoryModule
import com.example.di.ServiceModule.serviceModule
import com.example.di.UseCaseModule.useCaseModule
import com.example.marvelapp.di.ModelModule.modelModule
import com.example.marvelapp.di.ViewModelModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class BaseApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    viewModelModule,
                    useCaseModule,
                    serviceModule,
                    modelModule,
                    apiModule,
                    dataBaseModule,
                    repositoryModule
                )
            )
        }
    }
}

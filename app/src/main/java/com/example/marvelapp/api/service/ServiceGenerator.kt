package com.example.marvelapp.api.service

import com.example.marvelapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {
    private val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val defaultRequest = chain.request()
        val defaultHttpUrl = defaultRequest.url()
        val httpUrl = defaultHttpUrl.newBuilder()
            .addQueryParameter(TS, TS_VALUE)
            .addQueryParameter(KEY, BuildConfig.KEY_VALUE)
            .addQueryParameter(HASH, BuildConfig.HASH_VALUE)
            .build()
        val requestBuilder = defaultRequest.newBuilder().url(httpUrl)
        chain.proceed(requestBuilder.build())
    }

    private val builder = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder.client(httpClient.build()).build()
        return retrofit.create(serviceClass)
    }

    companion object {
        private const val TS = "ts"
        private const val TS_VALUE = "1"
        private const val KEY = "apikey"
        private const val HASH = "hash"
    }
}

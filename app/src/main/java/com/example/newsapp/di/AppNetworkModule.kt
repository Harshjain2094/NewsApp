package com.example.newsapp.di

import com.example.newsapp.utils.Utility
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppNetworkModule {

    private val timeOut: Long = 25

    @Provides
    @Singleton
    fun gson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
    }

    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun retrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    }

    private fun getBaseUrl(): String = Utility.BASE_URL

}
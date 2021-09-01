package com.example.newsapp.di

import com.example.newsapp.data.ApiService
import com.example.newsapp.data.RepositoryImpl
import com.example.newsapp.domain.NewsUseCase
import com.example.newsapp.domain.NewsUseCaseImpl
import com.example.newsapp.domain.Repository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [AppNetworkModule::class])
class AppModule {
    @Provides
    fun providesRepository(repositoryImpl: RepositoryImpl): Repository = repositoryImpl

    @Provides
    fun providesNewsUseCase(newsUseCaseImpl: NewsUseCaseImpl): NewsUseCase = newsUseCaseImpl

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
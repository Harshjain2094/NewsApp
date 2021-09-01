package com.example.newsapp.di

import com.example.newsapp.presentation.NewsActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, AppViewModelModule::class])
@Singleton
interface AppComponent {

    fun inject(newsActivity: NewsActivity)


    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }
}
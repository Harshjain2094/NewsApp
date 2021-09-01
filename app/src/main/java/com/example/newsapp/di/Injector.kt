package com.example.newsapp.di

import com.example.newsapp.presentation.NewsActivity

object Injector {
    private val baseComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

    fun inject(newsActivity: NewsActivity) {
        baseComponent.inject(newsActivity)
    }
}
package com.example.newsapp.domain

import com.example.newsapp.domain.model.News
import com.example.newsapp.utils.Result
import io.reactivex.Single

interface Repository {
    fun getNewsList(): Single<Result<List<News>>>
}
package com.example.newsapp.data

import com.example.newsapp.data.model.NewsDTO
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("carousell_news.json")
    fun getNewsList(): Single<List<NewsDTO>>
}
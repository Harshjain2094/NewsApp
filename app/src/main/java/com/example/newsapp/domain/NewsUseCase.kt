package com.example.newsapp.domain

import com.example.newsapp.domain.model.News
import com.example.newsapp.utils.Result
import io.reactivex.Single
import javax.inject.Inject

interface NewsUseCase {
    fun execute(): Single<Result<List<News>>>
}

class NewsUseCaseImpl @Inject constructor(private val repository: Repository) : NewsUseCase {
    override fun execute(): Single<Result<List<News>>> {
        return repository.getNewsList()
    }
}
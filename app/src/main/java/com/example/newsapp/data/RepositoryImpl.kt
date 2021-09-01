package com.example.newsapp.data

import com.example.newsapp.domain.Repository
import com.example.newsapp.domain.model.News
import com.example.newsapp.utils.Result
import com.example.newsapp.utils.convertResponseToResult
import io.reactivex.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val newsMapper: NewsMapper
) : Repository {
    override fun getNewsList(): Single<Result<List<News>>> {
        return apiService.getNewsList().convertResponseToResult {
            it?.map { newsDto ->
                newsMapper.toDomainModel(newsDto)
            }?.sortedByDescending { news ->
                news.creationTime
            }
        }
    }
}
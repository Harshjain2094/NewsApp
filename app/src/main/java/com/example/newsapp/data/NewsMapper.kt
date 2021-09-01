package com.example.newsapp.data

import com.example.newsapp.data.model.NewsDTO
import com.example.newsapp.domain.model.News
import com.example.newsapp.utils.DataMapper
import javax.inject.Inject

class NewsMapper @Inject constructor(): DataMapper<NewsDTO, News> {

    override fun toDomainModel(dtoModel: NewsDTO): News {
        return News(
            id = dtoModel.id,
            title = dtoModel.title,
            description = dtoModel.description,
            bannerUrl = dtoModel.bannerUrl,
            creationTime = dtoModel.timeCreated,
            rank = dtoModel.rank
        )
    }

    override fun toDTOModel(domainModel: News): NewsDTO {
        return NewsDTO(
            id = domainModel.id,
            title = domainModel.title,
            description = domainModel.description,
            bannerUrl = domainModel.bannerUrl,
            timeCreated = domainModel.creationTime,
            rank = domainModel.rank
        )
    }
}
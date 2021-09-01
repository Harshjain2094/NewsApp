package com.example.newsapp.domain.model

data class News(
    var id: Long,
    var title: String,
    var description: String,
    var bannerUrl: String,
    var creationTime: Long,
    var rank: Int,

) : BaseModel()


open class BaseModel(layoutResId: Int = 0)
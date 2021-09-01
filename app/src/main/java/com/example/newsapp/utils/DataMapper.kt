package com.example.newsapp.utils

interface DataMapper<T, R> {
    fun toDomainModel(dtoModel: T): R
    fun toDTOModel(domainModel: R): T
}
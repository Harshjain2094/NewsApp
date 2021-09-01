package com.example.newsapp.presentation.adapters

import android.view.View

interface BaseHandler<T> {
    fun onClick(view: View, data: T)
}
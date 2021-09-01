package com.example.newsapp.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

object Utility {
    const val BASE_URL = "https://storage.googleapis.com/carousell-interview-assets/android/"
    const val UNKNOWN_ERROR = "Unknown error"
    const val EMPTY_ERROR = "Data Not Found."
    const val EMPTY = ""

    @JvmStatic
    fun getReadableTime(creationTime: Long): String {
        val secondsDifference = (System.currentTimeMillis() / 1000) - creationTime
        val minuteDifference = secondsDifference / (60)
        val hourDifference = minuteDifference / 60
        val daysDifference = hourDifference / 24
        val monthDifference = daysDifference / 30
        val yearDifference = monthDifference / 12

        return when {
            secondsDifference < 60 -> "$secondsDifference seconds ago"
            minuteDifference < 60 -> "$minuteDifference minutes ago"
            hourDifference < 24 -> "$hourDifference hours ago"
            daysDifference < 30 -> "$daysDifference days ago"
            monthDifference < 12 -> "$monthDifference months ago"
            else -> "$yearDifference years ago"
        }
    }

    @JvmStatic
    @BindingAdapter("app:loadImage")
    fun loadImage(view: AppCompatImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(view)
    }

    sealed class SortType {
        object Popular : SortType()
        object Recent : SortType()
    }
}
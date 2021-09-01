package com.example.newsapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.BR
import com.example.newsapp.R
import com.example.newsapp.databinding.NewsListItemBinding
import com.example.newsapp.domain.model.News
import com.example.newsapp.presentation.adapters.BaseHandler

class NewsListRecyclerViewAdapter constructor(private val handler: BaseHandler<News>) :
    RecyclerView.Adapter<NewsListRecyclerViewAdapter.NewsViewHolder>() {

    var newsList = listOf<News>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<NewsListItemBinding>(
            layoutInflater,
            R.layout.news_list_item,
            parent,
            false
        )
        return NewsViewHolder(binding, handler)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = newsList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    inner class NewsViewHolder(
        viewBinding: ViewDataBinding,
        private val handler: BaseHandler<News>
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        private var mViewBinding: ViewDataBinding = viewBinding

        fun bind(item: News) {
            mViewBinding.setVariable(BR.obj, item)
            mViewBinding.setVariable(BR.handler, handler)
            mViewBinding.executePendingBindings()
        }
    }
}
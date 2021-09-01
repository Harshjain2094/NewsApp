package com.example.newsapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.BR
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityNewsBinding
import com.example.newsapp.di.Injector
import com.example.newsapp.domain.model.News
import com.example.newsapp.presentation.adapters.BaseHandler
import com.example.newsapp.utils.Utility
import com.example.newsapp.utils.showToast
import javax.inject.Inject

class NewsActivity : AppCompatActivity(), BaseHandler<News> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mBinding: ActivityNewsBinding
    private var mViewModel: NewsActivityViewModel? = null
    private var mAdapter: NewsListRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Injector.inject(this)
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this, viewModelFactory).get(NewsActivityViewModel::class.java)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_news)
        mBinding.setVariable(BR.viewModel, mViewModel)
        setupViews()
        startObservingLiveData()
    }

    private fun setupViews() {
        mAdapter = NewsListRecyclerViewAdapter(this)
        mBinding.rvNews.adapter = mAdapter
        mViewModel?.getNewsList()
    }

    private fun startObservingLiveData() {
        mViewModel?.getNewsLiveData()?.observe(this, Observer {
            mAdapter?.newsList = it
        })
    }

    override fun onClick(view: View, data: News) {
        when(view.id) {
            R.id.container -> {
                this.showToast(data.title)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.recent -> {
                mViewModel?.sortListBy(Utility.SortType.Recent)
                true
            }
            R.id.popular -> {
                mViewModel?.sortListBy(Utility.SortType.Popular)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
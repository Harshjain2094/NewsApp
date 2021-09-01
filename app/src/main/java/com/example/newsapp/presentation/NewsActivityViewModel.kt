package com.example.newsapp.presentation

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.domain.NewsUseCase
import com.example.newsapp.domain.model.News
import com.example.newsapp.utils.Result
import com.example.newsapp.utils.Utility
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NewsActivityViewModel @Inject constructor(private val newsUseCase: NewsUseCase) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val mNewsListLiveData: MutableLiveData<List<News>> = MutableLiveData()
    fun getNewsLiveData(): LiveData<List<News>> = mNewsListLiveData

    val isInProgress: ObservableBoolean = ObservableBoolean()
    val errorMessage: ObservableField<String> = ObservableField()

    fun getNewsList() {
        compositeDisposable.add(
            newsUseCase.execute()
                .delay(TIME_DELAY, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    isInProgress.set(true)
                }
                .doFinally {
                    isInProgress.set(false)
                }
                .subscribe { result ->
                    when (result) {
                        is Result.Success -> {
                            result.data?.let {
                                mNewsListLiveData.value = result.data
                                errorMessage.set(Utility.EMPTY)
                            } ?: errorMessage.set(Utility.EMPTY_ERROR)

                        }

                        is Result.Error -> {
                            errorMessage.set(result.throwable?.message ?: Utility.UNKNOWN_ERROR)
                        }
                    }
                })
    }

    fun sortListBy(sortBy: Utility.SortType) {
        (mNewsListLiveData.value)?.let { newsList ->
            mNewsListLiveData.value = when (sortBy) {
                is Utility.SortType.Popular -> {
                    newsList.sortedBy { it.rank }
                }
                else -> {
                    newsList.sortedByDescending { it.creationTime }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        private const val TIME_DELAY = 500L
    }
}
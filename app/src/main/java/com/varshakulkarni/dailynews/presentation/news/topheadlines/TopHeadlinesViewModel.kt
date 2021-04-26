package com.varshakulkarni.dailynews.presentation.news.topheadlines

import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.varshakulkarni.dailynews.data.NewsDataSource
import com.varshakulkarni.dailynews.di.viewmodel.AssistedViewModelFactory
import com.varshakulkarni.dailynews.di.viewmodel.hiltMavericksViewModelFactory
import com.varshakulkarni.dailynews.domain.TopHeadline
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

/**
 * This ViewModel Class manages top headlines state, consumes the NewsDataSource dependency
 */
class TopHeadlinesViewModel @AssistedInject constructor(
    @Assisted topHeadlinesState: TopHeadlinesState, private val newsDataSource: NewsDataSource
) : MavericksViewModel<TopHeadlinesState>(topHeadlinesState) {

    init {
        initTopHeadlines()
        getTopHeadlines()
    }

    private fun initTopHeadlines() {
        setState {
            copy(topHeadlines = Loading())
        }
    }

    fun getTopHeadlines() {
        suspend {
            newsDataSource.refreshTopHeadlines()
        }.execute {
            copy()
        }

        newsDataSource.getAllTopHeadlines().execute {
            copy(topHeadlines = it)
        }
    }

    fun updateReadList(topHeadline: TopHeadline) {
        suspend {
            newsDataSource.updateReadingList(topHeadline)
        }.execute { copy() }
    }

    fun getReadingList() = withState { state ->
        if (state.topHeadlines is Loading) return@withState
        newsDataSource.getReadingList().execute { copy(topHeadlines = it) }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<TopHeadlinesViewModel, TopHeadlinesState> {
        override fun create(state: TopHeadlinesState): TopHeadlinesViewModel
    }

    companion object :
        MavericksViewModelFactory<TopHeadlinesViewModel, TopHeadlinesState> by hiltMavericksViewModelFactory()
}

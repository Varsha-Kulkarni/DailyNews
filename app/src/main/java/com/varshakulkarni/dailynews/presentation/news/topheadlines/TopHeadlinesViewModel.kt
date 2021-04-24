package com.varshakulkarni.dailynews.presentation.news.topheadlines

import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.varshakulkarni.dailynews.data.repository.NewsRepository
import com.varshakulkarni.dailynews.di.viewmodel.AssistedViewModelFactory
import com.varshakulkarni.dailynews.di.viewmodel.hiltMavericksViewModelFactory
import com.varshakulkarni.dailynews.domain.TopHeadline
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class TopHeadlinesViewModel @AssistedInject constructor(
    @Assisted topHeadlinesState: TopHeadlinesState, private val newsRepository: NewsRepository
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
            newsRepository.refreshTopHeadlines()
        }.execute {
            copy()
        }

        newsRepository.getAllTopHeadlines().execute {
            copy(topHeadlines = it)
        }
    }

    fun updateReadList(topHeadline: TopHeadline) {
        suspend {
            newsRepository.updateReadingList(topHeadline)
        }.execute { copy() }
    }

    fun getReadingList() = withState { state ->
        if (state.topHeadlines is Loading) return@withState
        newsRepository.getReadingList().execute { copy(topHeadlines = it) }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<TopHeadlinesViewModel, TopHeadlinesState> {
        override fun create(state: TopHeadlinesState): TopHeadlinesViewModel
    }

    companion object :
        MavericksViewModelFactory<TopHeadlinesViewModel, TopHeadlinesState> by hiltMavericksViewModelFactory()
}

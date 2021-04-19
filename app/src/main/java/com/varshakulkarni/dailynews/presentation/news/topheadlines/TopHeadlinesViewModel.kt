package com.varshakulkarni.dailynews.presentation.news.topheadlines

import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.varshakulkarni.dailynews.data.repository.NewsRepository
import com.varshakulkarni.dailynews.di.viewmodel.AssistedViewModelFactory
import com.varshakulkarni.dailynews.di.viewmodel.hiltMavericksViewModelFactory
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

    private fun getTopHeadlines() {
        setState {
            copy(topHeadlines = Loading())
        }
    }

    private fun initTopHeadlines() {
        suspend {
            newsRepository.getTopHeadlines()
        }.execute { copy(topHeadlines = it) }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<TopHeadlinesViewModel, TopHeadlinesState> {
        override fun create(state: TopHeadlinesState): TopHeadlinesViewModel
    }

    companion object :
        MavericksViewModelFactory<TopHeadlinesViewModel, TopHeadlinesState> by hiltMavericksViewModelFactory()
}

package com.varshakulkarni.dailynews.presentation.news.sources

import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.varshakulkarni.dailynews.data.repository.NewsRepository
import com.varshakulkarni.dailynews.di.viewmodel.AssistedViewModelFactory
import com.varshakulkarni.dailynews.di.viewmodel.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class NewsSourcesViewModel @AssistedInject constructor(
    @Assisted newsSourcesState: NewsSourcesState, private val newsRepository: NewsRepository
) : MavericksViewModel<NewsSourcesState>(newsSourcesState) {

    init {
        initNewsSources()
        getNewsSources()
    }

    private fun initNewsSources() {
        setState {
            copy(newsSources = Loading())
        }
    }

    private fun getNewsSources() {
        suspend {
            newsRepository.getNewsSources()
        }.execute { copy(newsSources = it) }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<NewsSourcesViewModel, NewsSourcesState> {
        override fun create(state: NewsSourcesState): NewsSourcesViewModel
    }

    companion object :
        MavericksViewModelFactory<NewsSourcesViewModel, NewsSourcesState> by hiltMavericksViewModelFactory()
}

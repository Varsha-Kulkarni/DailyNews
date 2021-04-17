package com.varshakulkarni.dailynews.di

import com.varshakulkarni.dailynews.di.MavericksViewModelComponent
import com.varshakulkarni.dailynews.presentation.news.sources.NewsSourcesViewModel
import com.varshakulkarni.dailynews.presentation.news.topheadlines.TopHeadlinesViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap


@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TopHeadlinesViewModel::class)
    fun topHeadlinesViewModelFactory(factory: TopHeadlinesViewModel.Factory): AssistedViewModelFactory<*, *>
    @Binds
    @IntoMap
    @ViewModelKey(NewsSourcesViewModel::class)
    fun newsSourcesViewModelFactory(factory: NewsSourcesViewModel.Factory): AssistedViewModelFactory<*, *>

}
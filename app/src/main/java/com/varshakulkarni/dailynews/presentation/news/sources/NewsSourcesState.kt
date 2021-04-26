package com.varshakulkarni.dailynews.presentation.news.sources

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.varshakulkarni.dailynews.domain.NewsSource

/**
 *   This models the NewsSource screen as a function of NewsSourcesState
 */
data class NewsSourcesState(val newsSources: Async<List<NewsSource>> = Uninitialized) :
    MavericksState
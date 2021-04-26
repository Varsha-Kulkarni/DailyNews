package com.varshakulkarni.dailynews.presentation.news.topheadlines

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.varshakulkarni.dailynews.domain.TopHeadline

/**
 *   This models the TopHeadlines screen as a function of TopHeadlinesState
 */
data class TopHeadlinesState(val topHeadlines: Async<List<TopHeadline>> = Uninitialized) : MavericksState
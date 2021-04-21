package com.varshakulkarni.dailynews.data

import com.varshakulkarni.dailynews.domain.NewsSource
import com.varshakulkarni.dailynews.domain.TopHeadline
import kotlinx.coroutines.flow.Flow

interface NewsDataSource {
    suspend fun refreshTopHeadlines(): List<TopHeadline>
    fun getAllTopHeadlines(): Flow<List<TopHeadline>>
    suspend fun refreshNewsSources(): List<NewsSource>
    fun getAllNewsSources(): Flow<List<NewsSource>>
}
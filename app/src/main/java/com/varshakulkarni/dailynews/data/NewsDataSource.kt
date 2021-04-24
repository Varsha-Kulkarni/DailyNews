package com.varshakulkarni.dailynews.data

import com.varshakulkarni.dailynews.domain.NewsSource
import com.varshakulkarni.dailynews.domain.TopHeadline
import kotlinx.coroutines.flow.Flow

interface NewsDataSource {
    suspend fun refreshTopHeadlines(): List<Long>
    fun getAllTopHeadlines(): Flow<List<TopHeadline>>

    suspend fun refreshNewsSources(): List<Long>
    fun getAllNewsSources(): Flow<List<NewsSource>>

    suspend fun updateReadingList(topHeadline: TopHeadline): Int
    fun getReadingList(): Flow<List<TopHeadline>>

}
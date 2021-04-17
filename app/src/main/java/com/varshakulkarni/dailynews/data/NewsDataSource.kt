package com.varshakulkarni.dailynews.data

import com.varshakulkarni.dailynews.domain.NewsSource
import com.varshakulkarni.dailynews.domain.TopHeadline

interface NewsDataSource {
    suspend fun getTopHeadlines(): List<TopHeadline>
    suspend fun getNewsSources(): List<NewsSource>
}
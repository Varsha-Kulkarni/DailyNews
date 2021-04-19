package com.varshakulkarni.dailynews.data.repository

import com.varshakulkarni.dailynews.data.NewsDataSource
import com.varshakulkarni.dailynews.data.network.NewsApiService
import com.varshakulkarni.dailynews.domain.NewsSource
import com.varshakulkarni.dailynews.domain.TopHeadline
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApiService: NewsApiService) :
    NewsDataSource {

    override suspend fun getTopHeadlines(): List<TopHeadline> {
        val response = newsApiService.getTopHeadlines("in")
        return response.articles.map {
            TopHeadline(
                it.source,
                it.author,
                it.title,
                it.description,
                it.url,
                it.urlToImage,
                it.publishedAt,
                it.content
            )
        }
    }

    override suspend fun getNewsSources(): List<NewsSource> {

        val response = newsApiService.getSources()

        return response.sources.map {
            NewsSource(
                it.id,
                it.name,
                it.description,
                it.url,
                it.category,
                it.language,
                it.country
            )
        }
    }
}
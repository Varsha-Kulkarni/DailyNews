package com.varshakulkarni.dailynews.data.repository

import com.varshakulkarni.dailynews.data.NewsDataSource
import com.varshakulkarni.dailynews.data.db.NewsDatabase
import com.varshakulkarni.dailynews.data.network.NewsApiService
import com.varshakulkarni.dailynews.domain.NewsSource
import com.varshakulkarni.dailynews.domain.TopHeadline
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class NewsRepository @Inject constructor(
    private val newsApiService: NewsApiService,
    private val newsDatabase: NewsDatabase
) : NewsDataSource {

    override suspend fun refreshTopHeadlines(): List<TopHeadline> {
        val response = newsApiService.getTopHeadlines("in")
        val topHeadlines = response.articles.map {
            TopHeadline(
                it.title,
                it.source,
                it.author,
                it.description,
                it.url,
                it.urlToImage,
                it.publishedAt,
                it.content,
                false
            )
        }
        newsDatabase.topHeadlineDao.insertAll(*topHeadlines.toTypedArray())

        return topHeadlines
    }

    override fun getAllTopHeadlines(): Flow<List<TopHeadline>> {
        return newsDatabase.topHeadlineDao.getAllTopHeadlines()
    }

    override suspend fun refreshNewsSources(): List<NewsSource> {
        val response = newsApiService.getSources()

        val newsSources = response.sources.map {
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
        newsDatabase.newsSourceDao.insertAll(*newsSources.toTypedArray())

        return newsSources
    }

    override fun getAllNewsSources(): Flow<List<NewsSource>> {
        return newsDatabase.newsSourceDao.getAllNewsSources()
    }
}
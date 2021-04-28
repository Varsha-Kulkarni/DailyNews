package com.varshakulkarni.dailynews.data.repository

import com.varshakulkarni.dailynews.data.NewsDataSource
import com.varshakulkarni.dailynews.data.db.NewsDatabase
import com.varshakulkarni.dailynews.data.network.NewsApiService
import com.varshakulkarni.dailynews.domain.NewsSource
import com.varshakulkarni.dailynews.domain.TopHeadline
import com.varshakulkarni.dailynews.utils.getOneWeekOldDateFormatted
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * This Repository class is used to get top headlines, news sources and
 * reading list from [News API](https://newsapi.org).
 *
 * All the methods as implemented declared in `NewsDataSource` interface
 *
 * Database is used as single source of truth
 * Hilt is providing dependencies for `NewsApiService` and `NewsDatabase`
 */

class NewsRepository @Inject constructor(
    private val newsApiService: NewsApiService,
    private val newsDatabase: NewsDatabase
) : NewsDataSource {

    /**
     *  Gets the latest news from the API and inserts into the database
     *  Returns the list of IDs inserted.
     */
    override suspend fun refreshTopHeadlines(): List<Long> {
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
        return newsDatabase.topHeadlineDao.insertAll(*topHeadlines.toTypedArray())
    }

    /**
     *  Retrieves the top headlines from the database based on published date
     */
    override fun getAllTopHeadlines(): Flow<List<TopHeadline>> =
        newsDatabase.topHeadlineDao.getAllTopHeadlines()


    override suspend fun clearOldData(): Int {
        return newsDatabase.topHeadlineDao.deleteOldData(getOneWeekOldDateFormatted())
    }

    /**
     *  Gets the latest news sources from the API and inserts into the database
     *  Returns the list of IDs inserted.
     */
    override suspend fun refreshNewsSources(): List<Long> {
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
        return newsDatabase.newsSourceDao.insertAll(*newsSources.toTypedArray())
    }

    /**
     *  Retrieves the news sources from the database
     */
    override fun getAllNewsSources(): Flow<List<NewsSource>> =
        newsDatabase.newsSourceDao.getAllNewsSources()

    /**
     *  Add/Remove the news data from the Reading List based on boolean value isAddedToReadingList
     */
    override suspend fun updateReadingList(topHeadline: TopHeadline): Int {
        return if (topHeadline.isAddedToReadingList)
            newsDatabase.topHeadlineDao.removeFromReadingList(topHeadline.title)
        else newsDatabase.topHeadlineDao.addToReadingList(topHeadline.title)
    }

    /**
     *  Retrieves the news saved for reading later
     */
    override fun getReadingList(): Flow<List<TopHeadline>> =
        newsDatabase.topHeadlineDao.getReadingList()

}
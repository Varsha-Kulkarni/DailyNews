package com.varshakulkarni.dailynews.data.repository

import android.util.Log
import com.varshakulkarni.dailynews.data.NewsDataSource
import com.varshakulkarni.dailynews.data.remote.NewsApi
import com.varshakulkarni.dailynews.domain.NewsSource
import com.varshakulkarni.dailynews.domain.TopHeadline
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsRepository @Inject constructor() :
    NewsDataSource {

    private var topHeadlines = listOf<TopHeadline>()
    private var newsSources = listOf<NewsSource>()

    override suspend fun getTopHeadlines(): List<TopHeadline> {
        try {
            CoroutineScope(Dispatchers.IO).launch {

                val response = NewsApi.retrofitService.getTopHeadlines("in")
                topHeadlines = response.articles.map {
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
            Log.i("", "Headlinesssssss $topHeadlines")
        } catch (exception: java.lang.Exception) {
            Log.e("", "exception!!! ${exception.localizedMessage}")
        }
        return topHeadlines
    }

    override suspend fun getNewsSources(): List<NewsSource> {
        try {
            CoroutineScope(Dispatchers.IO).launch {

                val response = NewsApi.retrofitService.getSources()

                newsSources = response.sources?.map {
                    NewsSource(
                        it.id,
                        it.name,
                        it.description,
                        it.url,
                        it.category,
                        it.language,
                        it.country
                    )
                }!!
            }

            Log.i("", "$newsSources")
        } catch (exception: java.lang.Exception) {
            Log.e("", "exception!!! ${exception.localizedMessage}")
        }
        return newsSources
    }
}
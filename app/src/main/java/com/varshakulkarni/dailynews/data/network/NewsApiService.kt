package com.varshakulkarni.dailynews.data.network

import com.varshakulkarni.dailynews.data.network.models.NewsSourcesResponse
import com.varshakulkarni.dailynews.data.network.models.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String
    ): TopHeadlinesResponse

    @GET("sources")
    suspend fun getSources(
    ): NewsSourcesResponse
}

package com.varshakulkarni.dailynews.data.remote

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.varshakulkarni.dailynews.data.remote.models.NewsSourcesResponse
import com.varshakulkarni.dailynews.data.remote.models.TopHeadlinesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://newsapi.org/v2/"

private val gson = GsonBuilder().setLenient().create()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .client(NewsHttpClient.getClient())
    .baseUrl(BASE_URL)
    .build()


interface NewsApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
    ): TopHeadlinesResponse

    @GET("sources")
    suspend fun getSources(
    ): NewsSourcesResponse
}

object NewsApi {
    val retrofitService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}

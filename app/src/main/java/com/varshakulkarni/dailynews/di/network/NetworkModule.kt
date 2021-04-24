package com.varshakulkarni.dailynews.di.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.varshakulkarni.dailynews.BuildConfig
import com.varshakulkarni.dailynews.data.network.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * BASE URL to get News Data from newsapi.org
 * */
private const val BASE_URL = "https://newsapi.org/v2/"

/**
 * Retrieve the api key for newsapi.org defined in the local.properties
 * */
const val newsApiKey = BuildConfig.NewsApiKey

/**
 * Hilt module to provide Singleton instances of Network APIs
 * */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * provides shared instance of OkHttpClient which can be used for multiple Http calls
     * with custom settings.
     */
    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor { chain ->
            val original = chain.request()
            val url = original.url.newBuilder().addQueryParameter("apiKey", newsApiKey).build()
            val request = original.newBuilder().url(url).build()
            chain.proceed(request)
        }.build()
    }

    /**
     *  Provides the Retrofit instance for the Http requests declared in the `NewsApiService`
     *  interface. When `create()` is called Retrofit generates the implementations for the methods
     *  declared, based on the annotations for the [News](https://newsapi.org) endpoints
     */

    @Singleton
    @Provides
    fun provideNewsApiService(httpClient: OkHttpClient): NewsApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(httpClient)
            .baseUrl(BASE_URL)
            .build()
            .create(NewsApiService::class.java)
    }
}
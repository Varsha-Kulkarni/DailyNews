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

private const val BASE_URL = "https://newsapi.org/v2/"
const val newsApiKey = BuildConfig.NewsApiKey

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

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
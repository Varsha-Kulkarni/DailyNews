package com.varshakulkarni.dailynews.data.remote

import com.varshakulkarni.dailynews.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class NewsHttpClient : OkHttpClient() {

    companion object {

        const val newsApiKey = BuildConfig.NewsApiKey

        fun getClient(): OkHttpClient {
            val builder = OkHttpClient().newBuilder()

            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(interceptor)
            }
            return builder
                .addInterceptor { chain ->
                    val original = chain.request()
                    val url = original
                        .url
                        .newBuilder()
                        .addQueryParameter("apiKey", newsApiKey)
                        .build()
                    val request = original
                        .newBuilder()
                        .url(url)
                        .build()
                    chain.proceed(request)
                }
                .build()
        }

    }
}
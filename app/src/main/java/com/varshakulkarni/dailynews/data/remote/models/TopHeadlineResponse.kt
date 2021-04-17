package com.varshakulkarni.dailynews.data.remote.models

import com.google.gson.annotations.SerializedName

data class TopHeadlinesResponse(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<Article>
)

data class TopHeadlineSource(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
)

data class Article(
    @SerializedName("source") val source: TopHeadlineSource,
    @SerializedName("author") val author: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("content") val content: String
)
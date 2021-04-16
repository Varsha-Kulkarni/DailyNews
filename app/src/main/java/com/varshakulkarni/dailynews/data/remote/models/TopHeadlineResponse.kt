package com.varshakulkarni.dailynews.data.remote.models

import com.google.gson.annotations.SerializedName

data class TopHeadlinesResponse(
    @SerializedName("status") var status: String?,
    @SerializedName("articles") var articles: List<Article>?
)

data class Article(
    @SerializedName("source") var source: String?,
    @SerializedName("author") var author: String?,
    @SerializedName("title") var title: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("url") var url: String?,
    @SerializedName("urlToImage") var urlToImage: String?,
    @SerializedName("publishedAt") var publishedAt: String?,
    @SerializedName("content") var content: String?)
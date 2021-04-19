package com.varshakulkarni.dailynews.domain

import com.varshakulkarni.dailynews.data.network.models.TopHeadlineSource

data class TopHeadline(
    val source: TopHeadlineSource?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)
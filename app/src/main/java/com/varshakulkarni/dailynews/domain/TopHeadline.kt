package com.varshakulkarni.dailynews.domain

import com.varshakulkarni.dailynews.data.remote.models.TopHeadlineSource

data class TopHeadline(
    var source: TopHeadlineSource?,
    var author: String?,
    var title: String?,
    var description: String?,
    var url: String?,
    var urlToImage: String?,
    var publishedAt: String?,
    var content: String?
)
package com.varshakulkarni.dailynews.domain

import com.google.gson.annotations.SerializedName

data class TopHeadline(
    var source: String?,
    var author: String?,
    var title: String?,
    var description: String?,
    var url: String?,
    var urlToImage: String?,
    var publishedAt: String?,
    var content: String?
)
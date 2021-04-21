package com.varshakulkarni.dailynews.domain

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.varshakulkarni.dailynews.data.network.models.TopHeadlineSource

@Entity(tableName = "top_headlines")
data class TopHeadline(
    @PrimaryKey val title: String,
    @Embedded(prefix = "source") val source: TopHeadlineSource,
    val author: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
    val addToReadingList: Boolean
)
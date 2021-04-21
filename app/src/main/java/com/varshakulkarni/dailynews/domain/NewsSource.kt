package com.varshakulkarni.dailynews.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_sources")
data class NewsSource(
    @PrimaryKey val id: String,
    val name: String?,
    val description: String?,
    val url: String?,
    val category: String?,
    val language: String?,
    val country: String?
)
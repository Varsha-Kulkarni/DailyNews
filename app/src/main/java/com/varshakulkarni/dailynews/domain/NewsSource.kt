package com.varshakulkarni.dailynews.domain

data class NewsSource(
    var id: String?,
    var name: String?,
    var description: String?,
    var url: String?,
    var category: String?,
    var language: String?,
    var country: String?
)
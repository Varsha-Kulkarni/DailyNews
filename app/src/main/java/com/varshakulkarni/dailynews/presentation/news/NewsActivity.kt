package com.varshakulkarni.dailynews.presentation.news

import androidx.appcompat.app.AppCompatActivity
import com.varshakulkarni.dailynews.R
import dagger.hilt.android.AndroidEntryPoint

/**
 *   this NewsActivity hosts fragments showing top headlines, news sources and reading list
 *
 *   @AndroidEntryPoint annotation is required here, otherwise following runtime exception is thrown
 *   java.lang.IllegalStateException: Hilt Fragments must be attached to an @AndroidEntryPoint Activity.
 *   Found: class com.varshakulkarni.dailynews.presentation.news.NewsActivity
 */
@AndroidEntryPoint
class NewsActivity : AppCompatActivity(R.layout.activity_news)
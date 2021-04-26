package com.varshakulkarni.dailynews.presentation.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.varshakulkarni.dailynews.R
import com.varshakulkarni.dailynews.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity(R.layout.activity_news)
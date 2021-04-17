package com.varshakulkarni.dailynews.presentation.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.varshakulkarni.dailynews.R
import com.varshakulkarni.dailynews.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_news)

        binding.pager.adapter = createTabAdapter()

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 ->
                    tab.text = getString(R.string.top_headlines)
                1 ->
                    tab.text = getString(R.string.sources)

            }
            binding.pager.setCurrentItem(tab.position, true)
        }.attach()
    }

    private fun createTabAdapter(): NewsFragmentsAdapter {
        return NewsFragmentsAdapter(this)
    }
}
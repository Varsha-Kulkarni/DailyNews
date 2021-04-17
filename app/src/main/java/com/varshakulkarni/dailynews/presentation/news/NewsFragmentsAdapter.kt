package com.varshakulkarni.dailynews.presentation.news

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.varshakulkarni.dailynews.presentation.news.sources.NewsSourcesFragment
import com.varshakulkarni.dailynews.presentation.news.topheadlines.TopHeadlinesFragment


class NewsFragmentsAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return TopHeadlinesFragment.newInstance()
            }
            1 -> {
                return NewsSourcesFragment.newInstance()
            }

        }
        return TopHeadlinesFragment.newInstance()
    }

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    companion object {
        private const val NUM_TABS = 2
    }
}
package com.varshakulkarni.dailynews.presentation.news.sources

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.varshakulkarni.dailynews.R
import com.varshakulkarni.dailynews.databinding.ItemNewsSourceBinding
import com.varshakulkarni.dailynews.domain.NewsSource

/**
 *   adapter class to bind news sources data to the views
 */
class NewsSourcesListAdapter(private val clickListener: NewsSourceListener) :
    ListAdapter<NewsSource, NewsSourcesListAdapter.NewsSourceViewHolder>(NewsSourceDiffCallback) {

    class NewsSourceViewHolder(val viewDataBinding: ItemNewsSourceBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_news_source
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSourceViewHolder {
        val binding: ItemNewsSourceBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            NewsSourceViewHolder.LAYOUT,
            parent,
            false
        )
        return NewsSourceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsSourceViewHolder, position: Int) {
        holder.viewDataBinding.also {
            val newsSource = getItem(position)
            it.newsSource = newsSource
            it.clickListener = clickListener
        }
    }

    class NewsSourceListener(val block: (NewsSource) -> Unit) {
        fun onClick(newsSource: NewsSource) = block(newsSource)
    }

    companion object NewsSourceDiffCallback : DiffUtil.ItemCallback<NewsSource>() {
        override fun areItemsTheSame(oldItem: NewsSource, newItem: NewsSource) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: NewsSource, newItem: NewsSource) =
            oldItem == newItem

    }
}
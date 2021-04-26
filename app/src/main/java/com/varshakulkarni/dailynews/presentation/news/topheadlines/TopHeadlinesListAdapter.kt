package com.varshakulkarni.dailynews.presentation.news.topheadlines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.varshakulkarni.dailynews.R
import com.varshakulkarni.dailynews.databinding.ItemTopHeadlineBinding
import com.varshakulkarni.dailynews.domain.TopHeadline

/**
 *   adapter class to bind top headlines data to the views
 */
class TopHeadlinesListAdapter(private val clickListener: TopHeadlineClickListener) :
    ListAdapter<TopHeadline, TopHeadlinesListAdapter.TopHeadlinesViewHolder>(TopHeadlineDiffCallback) {

    class TopHeadlinesViewHolder(val viewDataBinding: ItemTopHeadlineBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val layout = R.layout.item_top_headline
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopHeadlinesViewHolder {
        val binding: ItemTopHeadlineBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            TopHeadlinesViewHolder.layout,
            parent,
            false
        )
        return TopHeadlinesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopHeadlinesViewHolder, position: Int) {
        holder.viewDataBinding.also {
            val topHeadline = getItem(position)
            it.topHeadline = topHeadline
            it.clickListener = clickListener
        }
    }

    companion object TopHeadlineDiffCallback : DiffUtil.ItemCallback<TopHeadline>() {
        override fun areItemsTheSame(oldItem: TopHeadline, newItem: TopHeadline): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: TopHeadline, newItem: TopHeadline): Boolean =
            oldItem == newItem
    }


    interface TopHeadlineClickListener {
        fun onClickNewsItem(topHeadline: TopHeadline)
        fun onClickReadingListButton(topHeadline: TopHeadline)
    }
}
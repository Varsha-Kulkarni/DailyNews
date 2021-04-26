package com.varshakulkarni.dailynews.presentation.news.sources

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.varshakulkarni.dailynews.R
import com.varshakulkarni.dailynews.databinding.FragmentSourcesBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment shows the news sources from [News API](https://newsapi.org)
 *
 * Implements MavericksView which has invalidate() method which handles UI state
 *
 * Implements TopHeadlineClickListener methods:
 * onClickNewsItem() defines the action when each of the news item is clicked
 * onClickReadingListButton defines the action to update the Reading List
 */
@AndroidEntryPoint
class NewsSourcesFragment : Fragment(), MavericksView {

    private var _binding: FragmentSourcesBinding? = null
    private val binding get() = _binding ?: error("null Binding")

    private val newsSourcesViewModel: NewsSourcesViewModel by fragmentViewModel()

    private lateinit var adapter: NewsSourcesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSourcesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NewsSourcesListAdapter(NewsSourcesListAdapter.NewsSourceListener {
            openUrl(it.url)
        })

        binding.apply {
            rvSources.adapter = adapter
            fabScrollUpSources.setOnClickListener {
                rvSources.smoothScrollToPosition(0)
            }
        }

    }

    private fun openUrl(url: String?) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    companion object {
        fun newInstance(): NewsSourcesFragment {
            return NewsSourcesFragment()
        }
    }

    override fun invalidate() {
        withState(newsSourcesViewModel) { state ->
            when (state.newsSources) {
                is Loading ->
                    binding.pbSourcesLoading.visibility = View.VISIBLE

                is Success -> {
                    binding.pbSourcesLoading.visibility = View.GONE
                    val sources = state.newsSources.invoke()
                    if (sources.isEmpty()) {
                        binding.tvEmptySources.visibility = View.VISIBLE
                    } else {
                        binding.tvEmptySources.visibility = View.GONE
                    }
                    adapter.submitList(sources)
                }

                is Fail -> {
                    binding.pbSourcesLoading.visibility = View.GONE
                    binding.tvEmptySources.visibility = View.VISIBLE
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.error_loading_sources),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}

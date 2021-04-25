package com.varshakulkarni.dailynews.presentation.news.topheadlines

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.varshakulkarni.dailynews.R
import com.varshakulkarni.dailynews.databinding.FragmentTopHeadlinesBinding
import com.varshakulkarni.dailynews.domain.TopHeadline
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopHeadlinesFragment : Fragment(), MavericksView,
    TopHeadlinesListAdapter.TopHeadlineClickListener {

    private var _binding: FragmentTopHeadlinesBinding? = null
    private val binding get() = _binding ?: error("null Binding")

    private val topHeadlinesViewModel: TopHeadlinesViewModel by fragmentViewModel()

    private lateinit var adapter: TopHeadlinesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        _binding = FragmentTopHeadlinesBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TopHeadlinesListAdapter(this)
        binding.apply{
            rvTopHeadlines.adapter = adapter

            refreshLayout.setOnRefreshListener {
                topHeadlinesViewModel.getTopHeadlines()
                binding.refreshLayout.isRefreshing = false
            }
            refreshLayout.setProgressBackgroundColorSchemeColor(
                resources.getColor(
                    R.color.colorPrimary,
                    context?.theme
                )
            )
            refreshLayout.setColorSchemeColors(Color.WHITE)
        }

    }

    private fun openUrl(url: String?) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }

    companion object {
        fun newInstance(): TopHeadlinesFragment {
            return TopHeadlinesFragment()
        }
    }

    override fun invalidate() {
        withState(topHeadlinesViewModel) { state ->
            when (state.topHeadlines) {
                is Loading -> {
                    binding.pbTopHeadlinesLoading.visibility = View.VISIBLE
                }

                is Success -> {
                    binding.pbTopHeadlinesLoading.visibility = View.GONE
                    val topHeadlines = state.topHeadlines.invoke()
                    if (topHeadlines.isEmpty()) {
                        binding.tvEmptyTopHeadlines.visibility = View.VISIBLE
                    } else {
                        binding.tvEmptyTopHeadlines.visibility = View.GONE
                    }
                    adapter.submitList(topHeadlines)

                }

                is Fail -> {
                    binding.pbTopHeadlinesLoading.visibility = View.GONE
                    binding.tvEmptyTopHeadlines.visibility = View.VISIBLE
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.error_loading_headlines),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onClickNewsItem(topHeadline: TopHeadline) {
        openUrl(topHeadline.url)
    }

    override fun onClickReadingListButton(topHeadline: TopHeadline) {
        topHeadlinesViewModel.updateReadList(topHeadline)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bookmarked -> {
                topHeadlinesViewModel.getReadingList()
            }
            R.id.all_top_headlines -> {
                topHeadlinesViewModel.getTopHeadlines()
            }
        }
        return true
    }
}
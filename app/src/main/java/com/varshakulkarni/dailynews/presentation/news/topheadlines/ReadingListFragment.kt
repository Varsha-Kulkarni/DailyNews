package com.varshakulkarni.dailynews.presentation.news.topheadlines

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
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
import com.varshakulkarni.dailynews.databinding.FragmentReadingListBinding
import com.varshakulkarni.dailynews.domain.TopHeadline
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReadingListFragment : Fragment(), MavericksView,
    TopHeadlinesListAdapter.TopHeadlineClickListener {

    private var _binding: FragmentReadingListBinding? = null
    private val binding get() = _binding ?: error("null Binding")

    private val topHeadlinesViewModel: TopHeadlinesViewModel by fragmentViewModel()

    private lateinit var adapter: TopHeadlinesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentReadingListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TopHeadlinesListAdapter(this)
        binding.apply {
            rvReadingList.adapter = adapter
            rvReadingList.isNestedScrollingEnabled = false


            fabScrollUpReadingList.setOnClickListener {
                rvReadingList.scrollToPosition(0)
            }
        }
    }

    private fun openUrl(url: String?) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }

    override fun invalidate() {
        withState(topHeadlinesViewModel) { state ->
            when (state.topHeadlines) {
                is Loading -> {
                    binding.pbReadingListLoading.visibility = View.VISIBLE
                }

                is Success -> {
                    binding.pbReadingListLoading.visibility = View.GONE
                    val topHeadlines = state.topHeadlines.invoke().filter {
                        it.isAddedToReadingList
                    }
                    if (topHeadlines.isEmpty()) {
                        binding.tvEmptyReadingList.visibility = View.VISIBLE
                    } else {
                        binding.tvEmptyReadingList.visibility = View.GONE
                    }
                    adapter.submitList(topHeadlines)
                }

                is Fail -> {
                    binding.pbReadingListLoading.visibility = View.GONE
                    binding.tvEmptyReadingList.visibility = View.VISIBLE
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

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }
}
package com.varshakulkarni.dailynews.presentation.news.topheadlines

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
import com.varshakulkarni.dailynews.databinding.FragmentTopheadlinesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopHeadlinesFragment : Fragment(), MavericksView {

    private var _binding: FragmentTopheadlinesBinding? = null
    private val binding get() = _binding ?: error("null Binding")

    private val topHeadlinesViewModel: TopHeadlinesViewModel by fragmentViewModel()

    private lateinit var adapter: TopHeadlinesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        _binding = FragmentTopheadlinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TopHeadlinesListAdapter(TopHeadlinesListAdapter.TopHeadlineClickListener {
            openUrl(it.url)
        })
        binding.lifecycleOwner = this
        binding.rvTopHeadlines.adapter = adapter

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
                    adapter.submitList(state.topHeadlines.invoke())
                }

                is Fail -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.error_loading_headlines),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }
}
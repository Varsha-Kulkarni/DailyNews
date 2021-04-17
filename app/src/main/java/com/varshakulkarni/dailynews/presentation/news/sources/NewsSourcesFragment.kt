package com.varshakulkarni.dailynews.presentation.news.sources

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.varshakulkarni.dailynews.databinding.FragmentSourcesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsSourcesFragment : Fragment(), MavericksView {

    private var _binding: FragmentSourcesBinding? = null
    private val binding get() = _binding ?: error("null Binding")

    private val newsSourcesViewModel: NewsSourcesViewModel by fragmentViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        _binding = FragmentSourcesBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }

    companion object {
        fun newInstance(): NewsSourcesFragment {
            return NewsSourcesFragment()
        }
    }

    override fun invalidate() {
        withState(newsSourcesViewModel){

        }
    }
}

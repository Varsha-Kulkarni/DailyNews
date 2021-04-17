package com.varshakulkarni.dailynews.presentation.news.topheadlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.varshakulkarni.dailynews.databinding.FragmentTopheadlinesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopHeadlinesFragment : Fragment(), MavericksView {

    private var _binding: FragmentTopheadlinesBinding? = null
    private val binding get() = _binding ?: error("null Binding")

    private val topHeadlinesViewModel: TopHeadlinesViewModel by fragmentViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        _binding = FragmentTopheadlinesBinding.inflate(inflater, container, false)
        return binding.root

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
        withState(topHeadlinesViewModel){

        }
    }
}
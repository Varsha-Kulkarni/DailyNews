package com.varshakulkarni.dailynews.presentation.news.topheadlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.varshakulkarni.dailynews.databinding.FragmentTopheadlinesBinding

class TopHeadlinesFragment : Fragment() {

    private var _binding: FragmentTopheadlinesBinding? = null
    private val binding get() = _binding ?: error("null Binding")

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
}
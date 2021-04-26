package com.varshakulkarni.dailynews.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.varshakulkarni.dailynews.R
import com.varshakulkarni.dailynews.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding ?: error("null Binding")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pager.adapter = createTabAdapter()

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 ->
                    tab.text = getString(R.string.top_headlines)
                1 ->
                    tab.text = getString(R.string.sources)

            }
            binding.pager.setCurrentItem(tab.position, true)
        }.attach()
    }

    private fun createTabAdapter(): NewsFragmentsAdapter {
        return NewsFragmentsAdapter(requireActivity())
    }
}
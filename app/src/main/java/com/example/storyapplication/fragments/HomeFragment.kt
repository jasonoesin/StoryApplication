package com.example.storyapplication.fragments

import StoryViewModel
import ViewModelFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storyapplication.adapter.LoadingStateAdapter
import com.example.storyapplication.adapter.StoryPagingAdapter
import com.example.storyapplication.databinding.FragmentHomeBinding
import androidx.fragment.app.viewModels

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        setupRv()
        getStories()

        return binding.root
    }

    private lateinit var storyAdapter : StoryPagingAdapter

    private val storyViewModel: StoryViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    private fun setupRv(){
        storyAdapter = StoryPagingAdapter(requireContext())
        ViewCompat.setNestedScrollingEnabled(binding.rvStory, false)
        binding.rvStory.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = storyAdapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    storyAdapter.retry()
                }
            )
        }
    }

    private fun getStories() {
        storyViewModel.stories.observe(requireActivity()) {
            storyAdapter.submitData(lifecycle, it)
        }
    }
}
package com.example.storyapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storyapplication.adapter.LoadingStateAdapter
import com.example.storyapplication.adapter.StoryAdapter
import com.example.storyapplication.data.MainViewModel
import com.example.storyapplication.data.ViewModelFactory
import com.example.storyapplication.databinding.FragmentHomeBinding
import com.example.storyapplication.utilities.LogUtil

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        getStories()

        return binding.root
    }

    private val mainViewModel: MainViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    private fun getStories() {
        val adapter = StoryAdapter(requireActivity())
        binding.rvStory.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        binding.rvStory.layoutManager = LinearLayoutManager(context)

        mainViewModel.story.observe(requireActivity()) {
            adapter.submitData(lifecycle, it)
        }
    }
}
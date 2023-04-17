package com.example.storyapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storyapplication.adapter.StoryAdapter
import com.example.storyapplication.databinding.FragmentHomeBinding
import com.example.storyapplication.responses.GetResponse
import com.example.storyapplication.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    private fun getStories() {
        val client = ApiConfig.getRetrofitApiHeader().getStories()

        client.enqueue(object : Callback<GetResponse> {
            override fun onResponse(
                call: Call<GetResponse>,
                response: Response<GetResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    val storyAdapter = StoryAdapter(requireContext(), responseBody.listStory)
                    binding.rvStory.adapter = storyAdapter
                    binding.rvStory.setHasFixedSize(true)
                    binding.rvStory.layoutManager = LinearLayoutManager(activity)
                    Log.d("JS22-1", "onSuccess: $responseBody")

                } else {
                    Log.d("JS22-1", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GetResponse>, t: Throwable) {

            }
        })
    }
}
package com.example.storyapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storyapplication.R
import com.example.storyapplication.adapter.StoryAdapter
import com.example.storyapplication.databinding.ActivityHomeBinding
import com.example.storyapplication.responses.GetResponse
import com.example.storyapplication.responses.MessageResponse
import com.example.storyapplication.retrofit.ApiConfig
import com.example.storyapplication.utilities.NavigationUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        getStories()

        setContentView(binding.root)
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
                    val storyAdapter = StoryAdapter(this@HomeActivity, responseBody.listStory)
                    binding.rvStory.adapter = storyAdapter
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
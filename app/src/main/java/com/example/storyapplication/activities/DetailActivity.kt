package com.example.storyapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.storyapplication.databinding.ActivityDetailBinding
import com.example.storyapplication.responses.DetailResponse
import com.example.storyapplication.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)

        getStory()

        setContentView(binding.root)
    }

    private fun getStory() {
        val client = ApiConfig.getRetrofitApiHeader().getStoryDetail(intent.getStringExtra("id")!!)

        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    binding.apply {
                        name.text = responseBody.story.name
                        description.text = responseBody.story.description
                    }

                    Glide.with(this@DetailActivity)
                        .load(responseBody.story.photoUrl)
                        .into(binding.image)

                } else {
                    Log.d("JS22-1", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {

            }
        })
    }
}
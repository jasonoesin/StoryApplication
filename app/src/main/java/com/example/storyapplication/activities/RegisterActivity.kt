package com.example.storyapplication.activities

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.storyapplication.databinding.ActivityRegisterBinding
import com.example.storyapplication.responses.MessageResponse
import com.example.storyapplication.retrofit.ApiConfig
import com.example.storyapplication.utilities.NavigationUtil

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)

        binding.toLogin.setOnClickListener {
            NavigationUtil.replaceActivityNoBack(this@RegisterActivity, MainActivity::class.java)
        }

        binding.registerBtn.setOnClickListener {
            register()
        }

        setContentView(binding.root)
    }

    private fun register() {
        val client = ApiConfig.getRetrofitApi().register(
            binding.name.text.toString(),
            binding.email.text.toString(),
            binding.password.text.toString())

        client.enqueue(object : Callback<MessageResponse> {

            override fun onResponse(
                call: Call<MessageResponse>,
                response: Response<MessageResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    Log.d("JS22-1", "onSuccess: $responseBody")
                    NavigationUtil.replaceActivityNoBack(this@RegisterActivity, HomeActivity::class.java)
                } else {
                    Log.d("JS22-1", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {

            }
        })
    }
}
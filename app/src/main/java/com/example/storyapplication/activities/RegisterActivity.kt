package com.example.storyapplication.activities

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.storyapplication.databinding.ActivityRegisterBinding
import com.example.storyapplication.responses.MessageResponse
import com.example.storyapplication.retrofit.ApiConfig
import com.example.storyapplication.utilities.NavigationUtil
import com.example.storyapplication.utilities.UserUtil

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)

        binding.toLogin.setOnClickListener {
            NavigationUtil.replaceActivityNoBack(this@RegisterActivity, MainActivity::class.java)
        }

        binding.registerBtn.setOnClickListener {
            if(binding.password.text!!.length >= 8)
                register()
            else
                Toast.makeText(this@RegisterActivity, "Password must be atleast 8 characters", Toast.LENGTH_SHORT).show()
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
                    NavigationUtil.replaceActivityNoBack(this@RegisterActivity, MainActivity::class.java)
                } else {
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {

            }
        })
    }
}
package com.example.storyapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.storyapplication.databinding.ActivityMainBinding
import com.example.storyapplication.responses.LoginResponse
import com.example.storyapplication.responses.MessageResponse
import com.example.storyapplication.retrofit.ApiConfig
import com.example.storyapplication.utilities.NavigationUtil
import com.example.storyapplication.utilities.PreferencesUtil
import com.example.storyapplication.utilities.UserUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val preferencesData = PreferencesUtil.getData(this@MainActivity)

        if(preferencesData.token != ""){
            UserUtil.set(preferencesData)
            NavigationUtil.replaceActivityNoBack(this@MainActivity, HomeActivity::class.java)
        }


        binding.toRegister.setOnClickListener {
            NavigationUtil.replaceActivityNoBack(this@MainActivity, RegisterActivity::class.java)
        }

        binding.loginBtn.setOnClickListener {
            if(binding.password.text!!.length >= 8)
                login()
            else
                Toast.makeText(this@MainActivity, "Password must be atleast 8 characters", Toast.LENGTH_SHORT).show()
        }

        setContentView(binding.root)
    }

    private fun login() {
        val client = ApiConfig.getRetrofitApi().login(
            binding.email.text.toString(),
            binding.password.text.toString())

        client.enqueue(object : Callback<LoginResponse> {

            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    Log.d("JS22-1", "onSuccess: $responseBody")
                    UserUtil.set(responseBody.loginResult)
                    PreferencesUtil.writeData(this@MainActivity, responseBody.loginResult)

                    NavigationUtil.replaceActivityNoBack(this@MainActivity, HomeActivity::class.java)
                } else {
                    Log.d("JS22-1", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

            }
        })
    }
}
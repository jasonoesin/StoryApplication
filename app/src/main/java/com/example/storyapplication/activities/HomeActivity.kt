package com.example.storyapplication.activities

import android.Manifest
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storyapplication.R
import com.example.storyapplication.adapter.StoryAdapter
import com.example.storyapplication.databinding.ActivityHomeBinding
import com.example.storyapplication.fragments.HomeFragment
import com.example.storyapplication.fragments.InsertFragment
import com.example.storyapplication.fragments.MapsFragment
import com.example.storyapplication.responses.GetResponse
import com.example.storyapplication.responses.MessageResponse
import com.example.storyapplication.retrofit.ApiConfig
import com.example.storyapplication.utilities.NavigationUtil
import com.example.storyapplication.utilities.PreferencesUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        changeFragment(HomeFragment())

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home-> changeFragment(HomeFragment())
                R.id.add-> changeFragment(InsertFragment())
                R.id.maps-> changeFragment(MapsFragment())
                R.id.logout-> {
                    NavigationUtil.replaceActivityNoBack(this@HomeActivity, MainActivity::class.java)
                    PreferencesUtil.deleteData(this@HomeActivity)
                }
            }
            true
        }

        setContentView(binding.root)
    }

    private fun changeFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
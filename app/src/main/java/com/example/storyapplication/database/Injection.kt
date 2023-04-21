package com.example.storyapplication.database
import android.content.Context
import com.example.storyapplication.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): StoryRepository {
        val database = StoryDatabase.getDatabase(context)
        val apiService = ApiConfig.getRetrofitApiHeader()
        return StoryRepository(database, apiService)
    }
}
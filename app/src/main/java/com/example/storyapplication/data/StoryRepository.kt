package com.example.storyapplication.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.storyapplication.responses.ListStoryItem
import com.example.storyapplication.retrofit.RetrofitApi

class StoryRepository(private val storyDatabase: StoryDatabase, private val apiService: RetrofitApi) {
    fun getStory():
            LiveData<PagingData<ListStoryItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                StoryPagingSource(apiService)
            }
        ).liveData
    }
}
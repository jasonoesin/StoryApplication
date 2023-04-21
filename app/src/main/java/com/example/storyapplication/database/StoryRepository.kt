package com.example.storyapplication.database

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.storyapplication.paging.StoryPagingSource
import com.example.storyapplication.responses.ListStoryItem
import com.example.storyapplication.retrofit.RetrofitApi

class StoryRepository(private val storyDatabase: StoryDatabase, private val apiService: RetrofitApi) {
    fun getStories(): LiveData<PagingData<ListStoryItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false,
                initialLoadSize = 5,
                maxSize = 15,
                prefetchDistance = 2,
            ),
            pagingSourceFactory = {
                StoryPagingSource(apiService)
            }
        ).liveData
    }
}
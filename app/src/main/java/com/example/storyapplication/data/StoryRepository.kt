package com.example.storyapplication.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.storyapplication.responses.ListStoryItem
import com.example.storyapplication.retrofit.RetrofitApi

class StoryRepository(private val storyDatabase: StoryDatabase, private val apiService: RetrofitApi) {
    @OptIn(ExperimentalPagingApi::class)
    fun getStory():
            LiveData<PagingData<ListStoryItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = StoryRemoteMediator(storyDatabase, apiService),
            pagingSourceFactory = {
                storyDatabase.storyDao().getAllStories()
            }
        ).liveData
    }
}
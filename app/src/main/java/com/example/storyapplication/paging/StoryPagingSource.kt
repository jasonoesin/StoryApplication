package com.example.storyapplication.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.storyapplication.responses.ListStoryItem
import com.example.storyapplication.retrofit.RetrofitApi

class StoryPagingSource(private val retrofitApi: RetrofitApi): PagingSource<Int, ListStoryItem>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, ListStoryItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListStoryItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = retrofitApi.getStories(page = position, size = params.loadSize)
            val prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1
            val nextKey = if (responseData.listStory.isEmpty()) null else position + 1
//
            Log.d("JS22-1", "Masuk : ${responseData.listStory}")
//            Log.d("JS22-1", "Masuk : ${prevKey}")
            Log.d("JS22-1", "Next : ${nextKey}")

            LoadResult.Page(
                data = responseData.listStory,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

}
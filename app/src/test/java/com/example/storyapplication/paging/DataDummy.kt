package com.example.storyapplication.paging

import com.example.storyapplication.responses.ListStoryItem

object DataDummy {
    fun generateDummyStories(): List<ListStoryItem> {
        val storiesList = ArrayList<ListStoryItem>()
        for (i in 0..10) {
            val story = ListStoryItem(
                "https://story-api.dicoding.dev/images/stories/photos-1682072466707_v9of9bTB.jpg",
                "2022-02-22T22:22:22Z",
                "Data $i",
                "https://www.dicoding.com/",
                1.2,
                "$i",
                1.2
            )
            storiesList.add(story)
        }
        return storiesList
    }
}
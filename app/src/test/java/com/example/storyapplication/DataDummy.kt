package com.example.storyapplication

import com.example.storyapplication.responses.ListStoryItem

object DataDummy {

    fun generateDummyData(): List<ListStoryItem> {
        val items: MutableList<ListStoryItem> = arrayListOf()
        for (i in 0..50) {
            val story = ListStoryItem(
                id = "quote $i",
                photoUrl = "https://picsum.photos/200",
                createdAt = "createdAt",
                name = "quote $i",
                description = "quote $i",
                lat = 0.0,
                lon = 0.0
            )
            items.add(story)
        }
        return items
    }
}
package com.example.storyapplication.responses

import com.google.gson.annotations.SerializedName

data class GetResponse(

	@field:SerializedName("listStory")
	val listStory: List<ListStoryItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

package com.example.storyapplication.retrofit

import com.example.storyapplication.responses.DetailResponse
import com.example.storyapplication.responses.GetResponse
import com.example.storyapplication.responses.LoginResponse
import com.example.storyapplication.responses.MessageResponse
import retrofit2.Call
import retrofit2.http.*

interface RetrofitApi {
    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<MessageResponse>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("stories")
    fun getStories(): Call<GetResponse>

    @GET("stories/{id}")
    fun getStoryDetail(
        @Path("id") id: String
    ): Call<DetailResponse>
}
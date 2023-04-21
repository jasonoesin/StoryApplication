package com.example.storyapplication.retrofit

import com.example.storyapplication.responses.DetailResponse
import com.example.storyapplication.responses.GetResponse
import com.example.storyapplication.responses.LoginResponse
import com.example.storyapplication.responses.MessageResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
    suspend fun getStories(
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
    ): GetResponse

    @GET("stories?location=1")
    fun getStoriesWithLocation(): Call<GetResponse>

    @GET("stories/{id}")
    fun getStoryDetail(
        @Path("id") id: String
    ): Call<DetailResponse>

    @Multipart
    @POST("stories")
    fun addStory(
        @Part("description") description: RequestBody,
        @Part photo: MultipartBody.Part,
        @Part("lat") lat: Float? = null,
        @Part("lon") lon: Float? = null,
    ): Call<MessageResponse>
}
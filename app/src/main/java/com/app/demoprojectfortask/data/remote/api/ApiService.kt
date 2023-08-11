package com.app.demoprojectfortask.data.remote.api

import com.app.demoprojectfortask.data.remote.models.PostResponseItem
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET("posts")
    suspend fun getPost(): Response<List<PostResponseItem>>
}
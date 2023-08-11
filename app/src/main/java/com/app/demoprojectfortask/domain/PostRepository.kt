package com.app.demoprojectfortask.domain

import com.app.demoprojectfortask.data.remote.api.ApiService
import com.app.demoprojectfortask.data.remote.models.PostResponseItem

import retrofit2.Response
import javax.inject.Inject


class PostRepository @Inject constructor(private val apiHelper: ApiService) {


    suspend fun getPost(): Response<List<PostResponseItem>> = apiHelper.getPost()
}
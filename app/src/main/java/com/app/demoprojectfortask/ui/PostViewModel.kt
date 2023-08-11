package com.app.demoprojectfortask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.demoprojectfortask.data.remote.models.PostResponseItem
import com.app.demoprojectfortask.domain.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel
class PostViewModel @Inject constructor(var repository: PostRepository) : ViewModel() {

    private val _postData = MutableStateFlow<UIState<List<PostResponseItem>>>(UIState.Loading)

    val postData: StateFlow<UIState<List<PostResponseItem>>>
        get() = _postData

    init {
        getPost()
    }


    fun getPost() {
        _postData.value = UIState.Loading
        viewModelScope.launch {
            try {
                repository.getPost().let {
                    if (it.isSuccessful && it.body() != null) {
                        _postData.value = UIState.Success(it.message(), it.body()!!)
                    } else _postData.value = UIState.Failure(it.message())
                }
            } catch (e: Exception) {
                _postData.value = UIState.Failure(e.message ?: "Unknown error")
            }


        }
    }
}
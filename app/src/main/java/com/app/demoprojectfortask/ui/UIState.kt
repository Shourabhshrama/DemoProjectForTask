package com.app.demoprojectfortask.ui

sealed class UIState<out T> {
    object Loading : UIState<Nothing>()
    data class Success<T>(val message: String,val data: T) : UIState<T>()
    data class Failure(val errorMessage: String) : UIState<Nothing>()
}

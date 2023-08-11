package com.app.demoprojectfortask.data.remote.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostResponseItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
): Parcelable
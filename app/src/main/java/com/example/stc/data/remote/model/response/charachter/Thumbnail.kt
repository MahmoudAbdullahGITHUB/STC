package com.example.stc.data.remote.model.response.charachter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbnail(
    val extension: String,
    val path: String
):Parcelable
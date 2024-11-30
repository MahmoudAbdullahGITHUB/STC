package com.example.stc.data.remote.model.response.charachter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Url(
    val type: String,
    val url: String
) : Parcelable
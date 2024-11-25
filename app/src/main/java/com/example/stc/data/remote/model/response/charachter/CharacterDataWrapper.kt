package com.example.stc.data.remote.model.response.charachter

data class CharacterDataWrapper(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)
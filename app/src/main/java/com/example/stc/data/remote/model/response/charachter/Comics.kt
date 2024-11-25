package com.example.stc.data.remote.model.response.charachter

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)
package com.example.stc.repository

import com.example.stc.data.remote.MarvelApiService
import com.example.stc.data.remote.model.response.charachter.CharacterDataWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val apiService: MarvelApiService
) {

    suspend fun getCharacters(
        apiKey: String, ts: String, hash: String,
        offset: Int, limit: Int,
    ): Response<CharacterDataWrapper?>? {
        return apiService.getCharacters(
            ts = ts,
            apiKey = apiKey,
            hash = hash,
            offset = offset,
            limit = limit,
        )
    }

}
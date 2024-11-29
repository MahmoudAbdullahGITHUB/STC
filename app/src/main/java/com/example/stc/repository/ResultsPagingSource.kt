package com.example.stc.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.stc.data.remote.MarvelApiService
import com.example.stc.data.remote.model.response.charachter.Result
import com.example.stc.utils.Constants
import com.example.stc.utils.Utils

//class ResultsPagingSource(
//    private val apiService: MarvelApiService
//) : PagingSource<Int, Result>() {
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
//        return try {
//            val nextPage = params.key ?: 1
//            val response =
//                apiService.getCharacters(
//                    ts = System.currentTimeMillis().toString(),
//                    apiKey = Constants.PUBLIC_API_KEY,
//                    hash = Utils.toMD5Hash(
//                        System.currentTimeMillis()
//                            .toString() + Constants.PRIVATE_API_KEY
//                                + Constants.PUBLIC_API_KEY
//                    ),
//                    offset = 0,
//                    limit = 20,
//                )!!.body()!!.data.results
//            LoadResult.Page(
//                data = response,
//                prevKey = null, // Only paging forward.
//                nextKey = if (response.isEmpty()) null else nextPage + 1
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
//        return null
//    }
//}

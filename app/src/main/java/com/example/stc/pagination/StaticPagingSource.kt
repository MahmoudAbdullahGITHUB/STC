package com.example.stc.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.stc.data.remote.MarvelApiService
import com.example.stc.data.remote.model.response.charachter.CharacterDataWrapper
import com.example.stc.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import com.example.stc.data.remote.model.response.charachter.Result
import com.example.stc.utils.Constants.PAGE_SIZE
import com.example.stc.utils.Utils

class StaticPagingSource @Inject constructor(
    private val apiService: MarvelApiService
) : PagingSource<Int, Result>() {

    private val _responseFlow = MutableStateFlow<CharacterDataWrapper?>(null)
    val responseFlow: StateFlow<CharacterDataWrapper?> = _responseFlow


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {


            val currentPage = params.key ?: 1

            val response = apiService.getCharacters(
                ts = System.currentTimeMillis().toString(),
                apiKey = Constants.PUBLIC_API_KEY,
                hash = Utils.toMD5Hash(
                    System.currentTimeMillis()
                        .toString() + Constants.PRIVATE_API_KEY
                            + Constants.PUBLIC_API_KEY
                ),
                offset = currentPage,
                limit = PAGE_SIZE,
            )

            _responseFlow.emit(response?.body())

            val resultsList = response?.body()?.data?.results ?: emptyList()
            Log.d(
                "StaticPagingSource", "Loaded page" +
                        " $resultsList: ${resultsList.size}"
            )

            LoadResult.Page(
                data = resultsList,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (currentPage == response?.body()?.data?.total) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

package com.example.stc.pagination

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.stc.data.remote.model.response.charachter.CharacterDataWrapper
import com.example.stc.data.remote.model.response.charachter.Result
import com.example.stc.utils.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val source: StaticPagingSource
) {

    fun getItems(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
//                prefetchDistance = 1
            ),
            pagingSourceFactory = { source }
        ).flow
    }

    fun getResponseFlow(): StateFlow<CharacterDataWrapper?> {
        return source.responseFlow
    }

}

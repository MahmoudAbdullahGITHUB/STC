package com.example.stc.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.stc.data.remote.MarvelApiService
import com.example.stc.data.remote.model.response.charachter.CharacterDataWrapper
import com.example.stc.data.remote.model.response.charachter.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject


//class CharactersRepository @Inject constructor(
//    private val apiService: MarvelApiService
//) : ResultRepository {
//    override fun getResults(): Flow<PagingData<Result>> = Pager(
//        config = PagingConfig(
//            pageSize = 20,
//            enablePlaceholders = false
//        ),
//        pagingSourceFactory = { ResultsPagingSource(apiService) }
//    ).flow
//}


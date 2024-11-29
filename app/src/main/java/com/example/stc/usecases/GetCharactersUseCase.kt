package com.example.stc.usecases

import androidx.paging.PagingData
import com.example.stc.data.remote.model.response.charachter.CharacterDataWrapper
import com.example.stc.data.remote.model.response.charachter.Result
import com.example.stc.pagination.DataRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class GetCharactersUseCase @Inject constructor(
    private val repository: DataRepository
) {
    operator fun invoke(): Flow<PagingData<Result>> =
        repository.getItems()

    fun getResponseFlow(): StateFlow<CharacterDataWrapper?> {
        return repository.getResponseFlow()
    }
}

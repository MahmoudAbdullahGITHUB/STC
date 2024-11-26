package com.example.stc.usecases

import com.example.stc.data.remote.MarvelApiService
import com.example.stc.data.remote.model.response.charachter.CharacterDataWrapper
import com.example.stc.repository.CharactersRepository
import com.example.stc.utils.ui.DataState
import com.itworxedu.core.ui.ErrorCode
import com.itworxedu.core.ui.ProgressBarState
import com.itworxedu.core.ui.ResponseCodeHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {

    fun getCharacters(
        ts: String,
        apiKey: String,
        hash: String,
        offset: Int, limit: Int,
    ): Flow<DataState<CharacterDataWrapper>> = flow {
        try {
            emit(DataState.Loading(ProgressBarState.Loading))
            val data = charactersRepository.getCharacters(
                ts = ts,
                apiKey = apiKey,
                hash = hash,
                offset= offset, limit= limit,
            )

            val responseType = ErrorCode.getCodeStatus(data?.code())

            emit(DataState.Loading(ProgressBarState.Idle))
            if (responseType == ResponseCodeHandler.SUCCESSFUL) {
                emit(DataState.Success(data?.body()))
            } else {
                emit(DataState.Error(responseType, responseType.name))
            }

        } catch (e: Exception) {
            emit(DataState.Loading(ProgressBarState.Idle))
            emit(DataState.Error(ResponseCodeHandler.SERVER_ERROR, e.message.toString()))
        }

    }.flowOn(Dispatchers.IO)
}
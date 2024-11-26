package com.example.stc.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stc.data.remote.model.response.charachter.CharacterDataWrapper
import com.example.stc.data.remote.model.response.charachter.Result
import com.example.stc.usecases.GetCharactersUseCase
import com.example.stc.utils.ui.DataState
import com.itworxedu.core.ui.ProgressBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _characters =
        MutableStateFlow<DataState<
                List<Result>>>(DataState.Loading(ProgressBarState.Idle))
    val characters: StateFlow<DataState<List<Result>>> = _characters

    fun fetchCharacters(
        ts: String, apiKey: String, hash: String,
        offset: Int, limit: Int,
    ) {
        println("what a hash = $hash")
        getCharactersUseCase
            .getCharacters(
                ts = ts, apiKey = apiKey, hash = hash,
                offset= offset, limit= limit,
            ).onEach { dataState ->
                println("?Sucesssssssssses")
                when (dataState) {
                    is DataState.Success -> {
                        dataState.data?.let {
                            _characters.value = DataState.Success(it.data.results)
                        }
                    }

                    is DataState.Error -> {
                        _characters.value =
                            DataState.Error(dataState.exception, dataState.message)

                    }

                    is DataState.Loading -> {
                        _characters.value =
                            DataState.Loading(dataState.progressBarState)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun validateCharacters(data: CharacterDataWrapper?) {
        data?.let {
            _characters.value = DataState.Success(data.data.results)
        }
    }


}
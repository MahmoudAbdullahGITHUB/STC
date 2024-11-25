package com.example.stc.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stc.data.remote.model.response.charachter.CharacterDataWrapper
import com.example.stc.usecases.GetCharactersUseCase
import com.example.stc.utils.Constants
import com.example.stc.utils.Utils
import com.example.stc.utils.ui.DataState
import com.itworxedu.core.ui.ProgressBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    private val _characters =
        MutableStateFlow<DataState<CharacterDataWrapper>>(DataState.Loading(ProgressBarState.Idle))
    val characters: StateFlow<DataState<CharacterDataWrapper>> = _characters.asStateFlow()


    init {
        /*fetchCharacters(
            Constants.PUBLIC_API_KEY,
            System.currentTimeMillis().toString(),
            Utils.toMD5Hash(
                System.currentTimeMillis()
                    .toString() + Constants.PRIVATE_API_KEY
                        + Constants.PUBLIC_API_KEY
            )
        )*/
    }

    fun fetchCharacters(ts: String, apiKey: String, hash: String) {
        println("what a hash = $hash")
        getCharactersUseCase
            .getCharacters(ts = ts, apiKey = apiKey, hash = hash).onEach { dataState ->
                println("?Sucesssssssssses")
                when (dataState) {
                    is DataState.Success -> {
                        _characters.value = DataState.Success(dataState.data)
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
}
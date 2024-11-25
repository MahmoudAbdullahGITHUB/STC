package com.example.stc.utils.ui

import com.itworxedu.core.ui.ProgressBarState
import com.itworxedu.core.ui.ResponseCodeHandler

sealed class DataState<T> {

    data class Error<T>(
        val exception: ResponseCodeHandler,
        val message: String
    ) : DataState<T>()

    data class Success<T>(
        val data: T? = null,
        val otherInfo: Any? = null

    ) : DataState<T>()

    data class Loading<T>(
        val progressBarState: ProgressBarState = ProgressBarState.Idle
    ) : DataState<T>()

}
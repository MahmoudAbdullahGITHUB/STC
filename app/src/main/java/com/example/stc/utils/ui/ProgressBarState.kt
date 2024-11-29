package com.example.stc.utils.ui

sealed class ProgressBarState {

    data object Loading: ProgressBarState()

    data object Idle: ProgressBarState()

}
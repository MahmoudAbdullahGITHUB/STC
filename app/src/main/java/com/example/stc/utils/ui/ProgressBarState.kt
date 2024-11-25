package com.itworxedu.core.ui

sealed class ProgressBarState {

    data object Loading: ProgressBarState()

    data object Idle: ProgressBarState()

}
package com.example.stc

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {

    companion object {
        lateinit var instance: MainApplication
    }
}
package com.example.simpletodoapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SimpleTodoApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
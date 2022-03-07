package com.example.check24app.ui

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Check24Application : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}
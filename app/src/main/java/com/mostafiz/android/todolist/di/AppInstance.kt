package com.mostafiz.android.todolist.di

import android.app.Application
import androidx.annotation.Keep
import dagger.hilt.android.HiltAndroidApp
@Keep
@HiltAndroidApp
class AppInstance :Application(){
    override fun onCreate() {
        super.onCreate()
    }
}
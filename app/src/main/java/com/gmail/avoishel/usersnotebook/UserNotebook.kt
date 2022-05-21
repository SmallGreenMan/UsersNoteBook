package com.gmail.avoishel.usersnotebook

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class UserNotebook: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
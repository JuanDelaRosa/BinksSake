package com.quetzapps.akibaroom

import akibaroom.core.utils.enviroment.AppContextProvider
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CollectorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppContextProvider.init(this)
    }
}

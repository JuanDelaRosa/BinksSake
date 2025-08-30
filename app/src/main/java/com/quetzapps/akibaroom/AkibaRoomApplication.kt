package com.quetzapps.akibaroom

import android.app.Application
import akibaroom.stores.di.storesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AkibaRoomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AkibaRoomApplication)
            modules(listOf(storesModule))
        }
    }
}

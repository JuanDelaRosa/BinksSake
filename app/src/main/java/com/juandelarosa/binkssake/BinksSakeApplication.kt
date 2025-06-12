package com.juandelarosa.binkssake

import android.app.Application
import binkssake.stores.di.storesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BinksSakeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BinksSakeApplication)
            modules(listOf(storesModule))
        }
    }
}

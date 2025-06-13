package com.juandelarosa.binkssake

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import binkssake.feature.stores.api.StoresApi
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val storesApi: StoresApi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, storesApi.create())
                .commit()
        }
    }
}

package com.juandelarosa.binkssake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import binkssake.core.ui.theme.BinksSakeTheme
import binkssake.feature.stores.api.StoresApi
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val storesApi: StoresApi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BinksSakeTheme {
                storesApi.Content()
            }
        }
    }
}

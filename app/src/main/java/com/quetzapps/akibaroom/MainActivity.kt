package com.quetzapps.akibaroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import akibaroom.core.ui.theme.AkibaRoomTheme
import akibaroom.feature.stores.api.StoresApi
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val storesApi: StoresApi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AkibaRoomTheme {
                storesApi.Content()
            }
        }
    }
}

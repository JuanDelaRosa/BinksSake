package com.quetzapps.akibaroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import akibaroom.core.ui.theme.AkibaRoomTheme
import akibaroom.feature.stores.api.StoresApi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var storesApi: StoresApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AkibaRoomTheme {
                storesApi.Content()
            }
        }
    }
}

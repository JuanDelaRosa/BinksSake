package com.quetzapps.akibaroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import akibaroom.core.ui.theme.CollectorTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import akibaroom.feature.figures.ui.navigation.FiguresEntry
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CollectorTheme {
                CollectorRoot()
            }
        }
    }
}

@Composable
fun CollectorRoot() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "figure") {
        composable("figure") { FiguresEntry() }
        composable("figure/{id}") { /* detail screen placeholder */ }
    }
}

package akibaroom.feature.auth.ui.navigation

import akibaroom.core.ui.compose.FeatureNavigation
import akibaroom.core.ui.navigation.navigateUpOrFinish
import akibaroom.feature.auth.ui.AuthViewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
internal fun AuthNavigation(
    viewModel: AuthViewModel
) {
    val navController = rememberNavController()
    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                AuthViewModel.ViewEffect.NavigateBack -> {
                    navController.navigateUpOrFinish()
                }
            }
        }
    }

    FeatureNavigation(
        navController = navController,
        startDestination = "auth",
    ) {
        composable("auth") {
            // Placeholder Auth Screen
            Text("Auth Screen - Coming Soon")
        }
    }
}

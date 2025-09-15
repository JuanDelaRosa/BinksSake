package akibaroom.feature.profile.ui.navigation

import akibaroom.core.ui.compose.FeatureNavigation
import akibaroom.core.ui.navigation.navigateUpOrFinish
import akibaroom.feature.profile.ui.ProfileViewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
internal fun ProfileNavigation(
    viewModel: ProfileViewModel
) {
    val navController = rememberNavController()
    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                ProfileViewModel.ViewEffect.NavigateBack -> {
                    navController.navigateUpOrFinish()
                }
            }
        }
    }

    FeatureNavigation(
        navController = navController,
        startDestination = "profile",
    ) {
        composable("profile") {
            // Placeholder Profile Screen
            Text("Profile Screen - Coming Soon")
        }
    }
}

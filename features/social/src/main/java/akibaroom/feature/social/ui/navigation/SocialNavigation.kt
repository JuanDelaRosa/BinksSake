package akibaroom.feature.social.ui.navigation

import akibaroom.core.ui.compose.FeatureNavigation
import akibaroom.core.ui.navigation.navigateUpOrFinish
import akibaroom.feature.social.ui.SocialViewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
internal fun SocialNavigation(
    viewModel: SocialViewModel
) {
    val navController = rememberNavController()
    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                SocialViewModel.ViewEffect.NavigateBack -> {
                    navController.navigateUpOrFinish()
                }
            }
        }
    }

    FeatureNavigation(
        navController = navController,
        startDestination = "social",
    ) {
        composable("social") {
            // Placeholder Social Screen
            Text("Social Screen - Coming Soon")
        }
    }
}

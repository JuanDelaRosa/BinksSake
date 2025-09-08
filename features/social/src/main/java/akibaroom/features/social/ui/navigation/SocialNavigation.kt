package akibaroom.features.social.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import akibaroom.core.ui.compose.FeatureNavigation
import akibaroom.core.ui.navigation.navigateUpOrFinish
import akibaroom.core.utils.extentions.requireActivity
import akibaroom.features.social.ui.SocialViewModel

@Composable
internal fun SocialNavigation(
    viewModel: SocialViewModel
) {
    val navController = rememberNavController()
    val state = viewModel.state.collectAsState().value
    val activity = LocalContext.current.requireActivity()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                SocialViewModel.ViewEffect.NavigateBack -> {
                    navController.navigateUpOrFinish(activity)
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
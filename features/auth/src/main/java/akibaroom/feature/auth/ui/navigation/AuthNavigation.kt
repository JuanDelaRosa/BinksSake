package akibaroom.feature.auth.ui.navigation

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
import akibaroom.feature.auth.ui.AuthViewModel

@Composable
internal fun AuthNavigation(
    viewModel: AuthViewModel
) {
    val navController = rememberNavController()
    val state = viewModel.state.collectAsState().value
    val activity = LocalContext.current.requireActivity()

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

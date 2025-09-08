package akibaroom.feature.store.ui.navigation

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
import akibaroom.feature.store.ui.StoreViewModel

@Composable
internal fun StoreNavigation(
    viewModel: StoreViewModel
) {
    val navController = rememberNavController()
    val state = viewModel.state.collectAsState().value
    val activity = LocalContext.current.requireActivity()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                StoreViewModel.ViewEffect.NavigateBack -> {
                    navController.navigateUpOrFinish(activity)
                }
            }
        }
    }

    FeatureNavigation(
        navController = navController,
        startDestination = "store",
    ) {
        composable("store") {
            // Placeholder Store Screen
            Text("Store Screen - Coming Soon")
        }
    }
}
package akibaroom.feature.store.ui.navigation

import akibaroom.core.ui.compose.FeatureNavigation
import akibaroom.core.ui.navigation.navigateUpOrFinish
import akibaroom.feature.store.ui.StoreViewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
internal fun StoreNavigation(
    viewModel: StoreViewModel
) {
    val navController = rememberNavController()
    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                StoreViewModel.ViewEffect.NavigateBack -> {
                    navController.navigateUpOrFinish()
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

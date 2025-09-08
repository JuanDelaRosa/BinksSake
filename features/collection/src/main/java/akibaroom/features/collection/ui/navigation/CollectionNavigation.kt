package akibaroom.features.collection.ui.navigation

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
import akibaroom.features.collection.ui.CollectionViewModel

@Composable
internal fun CollectionNavigation(
    viewModel: CollectionViewModel
) {
    val navController = rememberNavController()
    val state = viewModel.state.collectAsState().value
    val activity = LocalContext.current.requireActivity()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                CollectionViewModel.ViewEffect.NavigateBack -> {
                    navController.navigateUpOrFinish(activity)
                }
            }
        }
    }

    FeatureNavigation(
        navController = navController,
        startDestination = "collection",
    ) {
        composable("collection") {
            // Placeholder Collection Screen
            Text("Collection Screen - Coming Soon")
        }
    }
}
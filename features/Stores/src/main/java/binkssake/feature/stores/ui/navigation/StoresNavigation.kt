package binkssake.feature.stores.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import binkssake.core.ui.compose.FeatureNavigation
import binkssake.feature.stores.ui.StoreDetailScreen
import binkssake.feature.stores.ui.StoresViewModel
import binkssake.feature.stores.ui.StoresScreen

@Composable
internal fun StoresNavigation(
    navController: NavHostController = rememberNavController(),
    state: StoresViewModel.ViewState,
    executeAction: (StoresViewModel.Action) -> Unit,
) {
    FeatureNavigation(
        navController = navController,
        startDestination = ListOfStores.route,
    ) {
        composable(ListOfStores.route) {
            StoresScreen(
                state = state,
                executeAction = executeAction
            )
        }
        composable(
            route = StoreDetail.route,
            arguments = StoreDetail.arguments()
        ) { backStackEntry ->
            val index = StoreDetail.extractIndex(backStackEntry)
            StoreDetailScreen(
                store = state.sakeShops[index],
                executeAction = executeAction
            )
        }
    }
}



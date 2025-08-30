package akibaroom.feature.stores.ui.navigation

import android.content.Intent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import akibaroom.core.ui.compose.FeatureNavigation
import akibaroom.core.ui.navigation.navigateSafe
import akibaroom.core.ui.navigation.navigateUpOrFinish
import akibaroom.core.utils.extentions.requireActivity
import akibaroom.feature.stores.ui.StoreDetailScreen
import akibaroom.feature.stores.ui.StoresViewModel
import akibaroom.feature.stores.ui.StoresScreen
import androidx.core.net.toUri

@Composable
internal fun StoresNavigation(
    viewModel: StoresViewModel
) {
    val navController = rememberNavController()
    val state = viewModel.state.collectAsState().value
    val activity = LocalContext.current.requireActivity()

    LaunchedEffect(Unit) {
        viewModel.executeAction(StoresViewModel.Action.FetchStores)
        viewModel.effects.collect { effect ->
            when (effect) {
                is StoresViewModel.ViewEffect.OpenSakeShopDetails -> {
                    navController.navigateSafe(StoreDetail.build(effect.index))
                }
                StoresViewModel.ViewEffect.NavigateBack -> {
                    navController.navigateUpOrFinish(activity)
                }
                is StoresViewModel.ViewEffect.OpenAddressInMaps -> {
                    val intent = Intent(Intent.ACTION_VIEW, effect.address.toUri())
                    activity.startActivity(intent)
                }
                is StoresViewModel.ViewEffect.OpenWebsite -> {
                    val customTabsIntent = CustomTabsIntent.Builder().build()
                    customTabsIntent.launchUrl(activity, effect.website.toUri())
                }
            }
        }
    }

    FeatureNavigation(
        navController = navController,
        startDestination = ListOfStores.route,
    ) {
        composable(ListOfStores.route) {
            StoresScreen(
                state = state,
                executeAction = viewModel::executeAction
            )
        }
        composable(
            route = StoreDetail.route,
            arguments = StoreDetail.arguments()
        ) { backStackEntry ->
            val index = StoreDetail.extractIndex(backStackEntry)
            val store = state.sakeShops.getOrNull(index)
            if (store == null) {
                navController.popBackStack()
            } else {
                StoreDetailScreen(
                    store = store,
                    executeAction = viewModel::executeAction
                )
            }
        }
    }
}

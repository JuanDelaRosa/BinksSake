package akibaroom.core.ui.navigation

import akibaroom.core.utils.enviroment.AppContextProvider
import androidx.navigation.NavController

fun NavController.navigateUpOrFinish(): Boolean {
    val activity = AppContextProvider.activity
    if (!navigateUp()) activity.finish()
    return true
}

fun NavController.navigateSafe(route: String) {
    val currentRoute = currentBackStackEntry?.destination?.route
    if (currentRoute != route) {
        navigate(route)
    }
}

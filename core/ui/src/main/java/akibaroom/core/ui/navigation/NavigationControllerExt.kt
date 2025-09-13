package akibaroom.core.ui.navigation

import akibaroom.core.utils.enviroment.AppContextProvider
import akibaroom.core.utils.extentions.requireActivity
import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

fun NavController.navigateUpOrFinish(): Boolean {
    val activity = AppContextProvider.context.requireActivity()
    if (!navigateUp()) activity.finish()
    return true
}

fun NavController.navigateSafe(route: String) {
    val currentRoute = currentBackStackEntry?.destination?.route
    if (currentRoute != route) {
        navigate(route)
    }
}

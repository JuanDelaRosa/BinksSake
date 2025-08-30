package binkssake.core.ui.navigation

import android.app.Activity
import androidx.navigation.NavController

fun NavController.navigateUpOrFinish(activity: Activity): Boolean {
    if (!navigateUp()) activity.finish()
    return true
}

fun NavController.navigateSafe(route: String) {
    val currentRoute = currentBackStackEntry?.destination?.route
    if (currentRoute != route) {
        navigate(route)
    }
}

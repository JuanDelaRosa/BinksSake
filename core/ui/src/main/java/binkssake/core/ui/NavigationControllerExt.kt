package binkssake.core.ui

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController

fun NavController.navigateUpOrFinish(activity: FragmentActivity): Boolean {
    if (!navigateUp()) activity.finish()
    return true
}

fun NavController.navigateSafe(route: String) {
    val currentRoute = currentBackStackEntry?.destination?.route
    if (currentRoute != route) {
        navigate(route)
    }
}

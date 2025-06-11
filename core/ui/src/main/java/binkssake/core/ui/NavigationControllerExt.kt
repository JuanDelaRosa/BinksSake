package binkssake.core.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

private const val TAG = "NavControllerExt"

fun NavController.navigateUpOrFinish(activity: FragmentActivity): Boolean {
    if (!navigateUp()) activity.finish()
    return true
}

fun NavController.navigateSafe(
    actionResId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    navExtras: Navigator.Extras? = null
) {
    val navigationAction = currentDestination?.getAction(actionResId)
    if (navigationAction != null) {
        navigate(actionResId, args, navOptions, navExtras)
    } else {
        Log.e(TAG, "Action ID: $actionResId not found in current destination: $currentDestination")
    }
}

fun NavController.navigateSafe(
    action: NavDirections,
    navOptions: NavOptions? = null,
    navExtras: Navigator.Extras? = null
) {
    val navigationAction = currentDestination?.getAction(action.actionId)
    if (navigationAction != null) {
        navigate(action.actionId, action.arguments, navOptions, navExtras)
    } else {
        Log.e(TAG, "Current destination is null or action not found. Action ID: ${action.actionId}")
    }
}

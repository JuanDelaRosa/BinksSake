package binkssake.core.utils.extentions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

fun Context.findActivity(): Activity? {
    var currentContext = this
    while (currentContext is ContextWrapper) {
        if (currentContext is Activity) return currentContext
        currentContext = currentContext.baseContext
    }
    return null
}

fun Context.requireActivity(): Activity {
    return findActivity() ?: error("Context is not associated with an Activity")
}

package binkssake.core.utils.extentions

import android.content.Context
import android.content.ContextWrapper
import androidx.fragment.app.FragmentActivity

fun Context.findFragmentActivity(): FragmentActivity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is FragmentActivity) return context
        context = context.baseContext
    }
    return null
}

fun Context.requireFragmentActivity(): FragmentActivity {
    return findFragmentActivity()
        ?: error("Context is not associated with a FragmentActivity")
}

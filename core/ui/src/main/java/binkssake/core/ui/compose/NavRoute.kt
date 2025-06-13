package binkssake.core.ui.compose

import androidx.navigation.NamedNavArgument

interface NavRoute {
    val route: String
    fun arguments(): List<NamedNavArgument> = emptyList()
    fun build(vararg args: Any?): String = route
}

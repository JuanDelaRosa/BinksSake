package binkssake.feature.stores.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import binkssake.core.ui.theme.BinksSakeTheme

internal class StoresListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val navController = rememberNavController()
                BinksSakeTheme {
                    Scaffold {
                        NavHost(
                            navController = navController,
                            startDestination = "home",
                            modifier = Modifier.padding(it)
                        ) {
                            composable("home") {
                                StoresScreen()
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance(): StoresListFragment {
            return StoresListFragment()
        }
    }
}

package binkssake.feature.stores.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import binkssake.core.ui.viewmodel.observe
import binkssake.core.ui.viewmodel.viewModels
import binkssake.core.ui.theme.utils.createBinksSakeComposeView

internal class StoresListFragment : Fragment() {

    private val viewModel by viewModels<StoresListViewModel> {
        StoresListViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        createBinksSakeComposeView(requireContext()) {
            val navController = rememberNavController()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.effects.observe(viewLifecycleOwner, action = ::handleViewModelEffects)
        viewModel.executeAction(StoresListViewModel.Action.FetchStores)
    }

    private fun handleViewModelEffects(effect: StoresListViewModel.ViewEffect) {
        when (effect) {
            is StoresListViewModel.ViewEffect.OpenSakeShopDetails -> {
            }
            is StoresListViewModel.ViewEffect.CloseScreen -> {
            }
        }
    }

    companion object {
        fun newInstance(): StoresListFragment {
            return StoresListFragment()
        }
    }
}

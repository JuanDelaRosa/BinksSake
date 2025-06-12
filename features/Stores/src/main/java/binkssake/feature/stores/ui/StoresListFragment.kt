package binkssake.feature.stores.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import binkssake.core.ui.navigateSafe
import binkssake.core.ui.navigateUpOrFinish
import binkssake.core.ui.theme.utils.createBinksSakeComposeView
import binkssake.core.ui.viewmodel.viewModels

internal class StoresListFragment : Fragment() {

    private val viewModel by viewModels<StoresListViewModel> {
        StoresListViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        createBinksSakeComposeView(requireContext()) {
            val navController = rememberNavController()
            val state by viewModel.state.collectAsState()
            val effect = viewModel.effects.collectAsStateWithLifecycle(null).value

            LaunchedEffect(effect) {
                effect?.let {
                    handleViewModelEffects(it, navController)
                }
            }

            Scaffold { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "home",
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable("home") {
                        BackHandler {
                            viewModel.executeAction(StoresListViewModel.Action.BackClicked)
                        }
                        StoresScreen(
                            state = state,
                            executeAction = viewModel::executeAction
                        )
                    }

                    composable("detail") {
                        BackHandler {
                            viewModel.executeAction(StoresListViewModel.Action.BackClicked)
                        }
                        val selected = state.selected
                        if (selected == null) {
                            viewModel.executeAction(StoresListViewModel.Action.BackClicked)
                        } else {
                            StoreDetailScreen(store = selected)
                        }
                    }
                }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.executeAction(StoresListViewModel.Action.FetchStores)
    }

    private fun handleViewModelEffects(
        effect: StoresListViewModel.ViewEffect,
        navController: NavController
    ) {
        when (effect) {
            is StoresListViewModel.ViewEffect.OpenSakeShopDetails -> {
                navController.navigateSafe("detail")
            }

            StoresListViewModel.ViewEffect.NavigateBack -> {
                navController.navigateUpOrFinish(requireActivity())
            }
        }
    }

    companion object {
        fun newInstance(): StoresListFragment {
            return StoresListFragment()
        }
    }
}

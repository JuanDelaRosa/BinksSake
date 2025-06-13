package binkssake.feature.stores.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import binkssake.core.ui.navigation.navigateSafe
import binkssake.core.ui.navigation.navigateUpOrFinish
import binkssake.core.ui.theme.utils.createBinksSakeComposeView
import binkssake.core.ui.viewmodel.observe
import binkssake.core.ui.viewmodel.viewModels
import binkssake.feature.stores.ui.navigation.StoreDetail
import binkssake.feature.stores.ui.navigation.StoresNavigation

internal class StoresFragment : Fragment() {

    private lateinit var navController: NavHostController

    private val viewModel by viewModels<StoresViewModel> {
        StoresViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        createBinksSakeComposeView(requireContext()) {
            navController = rememberNavController()
            StoresNavigation(
                navController = navController,
                state = viewModel.state.collectAsState().value,
                executeAction = viewModel::executeAction
            )
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.executeAction(StoresViewModel.Action.FetchStores)
        viewModel.effects.observe(viewLifecycleOwner) { effect ->
            handleViewModelEffects(effect, navController)
        }
    }

    private fun handleViewModelEffects(
        effect: StoresViewModel.ViewEffect,
        navController: NavController
    ) {
        when (effect) {
            is StoresViewModel.ViewEffect.OpenSakeShopDetails -> {
                navController.navigateSafe(StoreDetail.build(effect.index))
            }
            StoresViewModel.ViewEffect.NavigateBack -> {
                navController.navigateUpOrFinish(requireActivity())
            }
        }
    }

    companion object {
        fun newInstance() = StoresFragment()
    }
}

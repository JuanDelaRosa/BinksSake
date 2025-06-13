package binkssake.feature.stores.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import binkssake.core.ui.theme.utils.setBinksSakeContent
import binkssake.core.ui.viewmodel.viewModels
import binkssake.feature.stores.ui.navigation.StoresNavigation

internal class StoresFragment : Fragment() {

    private lateinit var navController: NavHostController

    private val viewModel by viewModels {
        StoresViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner))
            setBinksSakeContent {
                StoresNavigation(
                    viewModel = viewModel
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.executeAction(StoresViewModel.Action.FetchStores)
        }
    }

    companion object {
        fun newInstance() = StoresFragment()
    }
}

package akibaroom.feature.profile.di

import akibaroom.feature.profile.api.ProfileApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import akibaroom.feature.profile.ui.ProfileViewModel
import akibaroom.feature.profile.ui.navigation.ProfileNavigation
import javax.inject.Inject

class ProfileApiImpl @Inject constructor() : ProfileApi {
    @Composable
    override fun Content() {
        val viewModel: ProfileViewModel = hiltViewModel()
        ProfileNavigation(viewModel = viewModel)
    }
}

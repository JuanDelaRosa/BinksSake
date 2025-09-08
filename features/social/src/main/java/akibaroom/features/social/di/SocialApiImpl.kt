package akibaroom.features.social.di

import akibaroom.features.social.api.SocialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import akibaroom.features.social.ui.SocialViewModel
import akibaroom.features.social.ui.navigation.SocialNavigation
import javax.inject.Inject

class SocialApiImpl @Inject constructor() : SocialApi {
    @Composable
    override fun Content() {
        val viewModel: SocialViewModel = hiltViewModel()
        SocialNavigation(viewModel = viewModel)
    }
}

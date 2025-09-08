package akibaroom.feature.social.di

import akibaroom.feature.social.api.SocialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import akibaroom.feature.social.ui.SocialViewModel
import akibaroom.feature.social.ui.navigation.SocialNavigation
import javax.inject.Inject

class SocialApiImpl @Inject constructor() : SocialApi {
    @Composable
    override fun Content() {
        val viewModel: SocialViewModel = hiltViewModel()
        SocialNavigation(viewModel = viewModel)
    }
}

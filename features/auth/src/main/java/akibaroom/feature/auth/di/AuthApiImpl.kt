package akibaroom.feature.auth.di

import akibaroom.feature.auth.api.AuthApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import akibaroom.feature.auth.ui.AuthViewModel
import akibaroom.feature.auth.ui.navigation.AuthNavigation
import javax.inject.Inject

class AuthApiImpl @Inject constructor() : AuthApi {
    @Composable
    override fun Content() {
        val viewModel: AuthViewModel = hiltViewModel()
        AuthNavigation(viewModel = viewModel)
    }
}
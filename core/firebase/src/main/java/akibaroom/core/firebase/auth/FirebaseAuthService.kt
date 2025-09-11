package akibaroom.core.firebase.auth

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthService @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    
    /**
     * Get the current authenticated user
     */
    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser
    
    /**
     * Check if user is authenticated
     */
    fun isUserAuthenticated(): Boolean = firebaseAuth.currentUser != null
    
    /**
     * Get user authentication state as Flow
     */
    fun getAuthStateFlow(): Flow<FirebaseUser?> = flow {
        firebaseAuth.addAuthStateListener { auth ->
            // This will be handled by the calling code
        }
    }
    
    /**
     * Create sign-in intent for FirebaseUI
     */
    fun createSignInIntent(context: Context): Intent {
        val providers = listOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build()
        )
        
        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
    }
    
    /**
     * Sign out the current user
     */
    suspend fun signOut(context: Context) {
        AuthUI.getInstance().signOut(context)
    }
    
    /**
     * Delete the current user account
     */
    suspend fun deleteAccount(context: Context) {
        AuthUI.getInstance().delete(context)
    }
    
    /**
     * Get user ID token
     */
    suspend fun getIdToken(): String? {
        return firebaseAuth.currentUser?.getIdToken(false)?.await()?.token
    }
    
    /**
     * Get user ID token with force refresh
     */
    suspend fun getIdToken(forceRefresh: Boolean): String? {
        return firebaseAuth.currentUser?.getIdToken(forceRefresh)?.await()?.token
    }
}

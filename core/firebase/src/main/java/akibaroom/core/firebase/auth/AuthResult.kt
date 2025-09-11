package akibaroom.core.firebase.auth

import com.google.firebase.auth.FirebaseUser

/**
 * Sealed class representing authentication results
 */
sealed class AuthResult {
    data class Success(val user: FirebaseUser) : AuthResult()
    data class Error(val exception: Exception) : AuthResult()
    object Cancelled : AuthResult()
}

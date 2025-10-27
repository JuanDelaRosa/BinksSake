package akibaroom.feature.auth.domain.model

import akibaroom.core.domain.model.UserType

data class AuthUser(
    val uid: String,
    val email: String,
    val displayName: String?,
    val photoUrl: String?,
    val isEmailVerified: Boolean
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val email: String,
    val password: String,
    val displayName: String,
    val username: String,
    val userType: UserType
)

data class AuthResult(
    val success: Boolean,
    val user: AuthUser? = null,
    val error: AuthError? = null
)

sealed class AuthError {
    data class InvalidCredentials(val message: String) : AuthError()
    data class UserNotFound(val message: String) : AuthError()
    data class EmailAlreadyInUse(val message: String) : AuthError()
    data class WeakPassword(val message: String) : AuthError()
    data class NetworkError(val message: String) : AuthError()
    data class Unknown(val message: String) : AuthError()
}

data class OnboardingData(
    val displayName: String = "",
    val username: String = "",
    val userType: UserType = UserType.COLLECTOR,
    val bio: String = "",
    val location: String = "",
    val acceptedTerms: Boolean = false
)

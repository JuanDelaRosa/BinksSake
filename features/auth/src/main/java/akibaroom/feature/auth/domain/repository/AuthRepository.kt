package akibaroom.feature.auth.domain.repository

import akibaroom.feature.auth.domain.model.AuthResult
import akibaroom.feature.auth.domain.model.AuthUser
import akibaroom.feature.auth.domain.model.LoginRequest
import akibaroom.feature.auth.domain.model.RegisterRequest
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun getCurrentUser(): Flow<AuthUser?>

    suspend fun login(request: LoginRequest): AuthResult

    suspend fun register(request: RegisterRequest): AuthResult

    suspend fun loginWithGoogle(): AuthResult

    suspend fun sendPasswordResetEmail(email: String): Result<Unit>

    suspend fun sendEmailVerification(): Result<Unit>

    suspend fun logout()

    suspend fun deleteAccount(): Result<Unit>

    fun isUserLoggedIn(): Boolean
}

package akibaroom.core.firebase.example

import android.content.Context
import akibaroom.core.firebase.auth.FirebaseAuthService
import akibaroom.core.firebase.database.FirebaseDatabaseService
import akibaroom.core.firebase.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Simple example showing how to use the Firebase services
 * This class demonstrates the basic usage patterns
 */
class SimpleUsageExample @Inject constructor(
    private val authService: FirebaseAuthService,
    private val databaseService: FirebaseDatabaseService
) {
    
    /**
     * Example of authentication flow
     */
    fun demonstrateAuthentication(context: Context) {
        // Check if user is authenticated
        val isAuthenticated = authService.isUserAuthenticated()
        
        if (!isAuthenticated) {
            // Create sign-in intent (this would be used with ActivityResultLauncher)
            val signInIntent = authService.createSignInIntent(context)
            // Launch the intent in your Activity/Fragment
        } else {
            // User is authenticated, get current user
            val currentUser = authService.getCurrentUser()
            currentUser?.let { user ->
                println("User is authenticated: ${user.email}")
            }
        }
    }
    
    /**
     * Example of database operations
     */
    fun demonstrateDatabaseOperations() {
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        
        coroutineScope.launch {
            try {
                // Example: Write user data
                val userId = "example_user_123"
                val userData = mapOf(
                    "name" to "John Doe",
                    "email" to "john@example.com",
                    "createdAt" to System.currentTimeMillis()
                )
                
                databaseService.writeData("users/$userId", userData)
                println("User data written successfully")
                
                // Example: Read user data
                val snapshot = databaseService.readData("users/$userId")
                snapshot?.let {
                    val name = it.child("name").getValue(String::class.java)
                    val email = it.child("email").getValue(String::class.java)
                    println("Read user: $name, $email")
                }
                
                // Example: Listen to real-time changes
                databaseService.listenToData("users/$userId").collect { snapshot ->
                    snapshot?.let {
                        val name = it.child("name").getValue(String::class.java)
                        println("Real-time update: User name is now $name")
                    }
                }
                
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }
    
    /**
     * Example of using the User model
     */
    fun demonstrateUserModel() {
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        
        coroutineScope.launch {
            try {
                val user = User(
                    uid = "user_123",
                    email = "user@example.com",
                    displayName = "Example User",
                    photoUrl = "https://example.com/photo.jpg"
                )
                
                // Write user object to database
                databaseService.writeData("users/${user.uid}", user)
                println("User object written to database")
                
                // Read user object from database
                val snapshot = databaseService.readData("users/${user.uid}")
                snapshot?.let {
                    val readUser = it.getValue(User::class.java)
                    readUser?.let { user ->
                        println("Read user: ${user.displayName} (${user.email})")
                    }
                }
                
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }
    
    /**
     * Example of sign out
     */
    fun demonstrateSignOut(context: Context) {
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        
        coroutineScope.launch {
            try {
                authService.signOut(context)
                println("User signed out successfully")
            } catch (e: Exception) {
                println("Error signing out: ${e.message}")
            }
        }
    }
}

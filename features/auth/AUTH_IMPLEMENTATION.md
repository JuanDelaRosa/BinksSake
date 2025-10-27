# Auth Feature Module - Implementación

## 🎯 Funcionalidades

- Login con Email/Password
- Login con Google (OAuth)
- Login con Phone (SMS)
- Registro de nuevos usuarios
- Onboarding con selección de tipo de usuario (Collector/Store)
- Recuperación de contraseña
- Verificación de email
- Gestión de sesión persistente
- Logout
- Eliminación de cuenta

## 📦 Estructura

```
features/auth/
├── domain/
│   ├── model/
│   │   └── AuthModels.kt          # AuthUser, LoginRequest, RegisterRequest
│   ├── repository/
│   │   └── AuthRepository.kt      # Interfaz del repositorio
│   └── usecase/
│       ├── LoginUseCase.kt
│       ├── RegisterUseCase.kt
│       ├── LogoutUseCase.kt
│       └── GetCurrentUserUseCase.kt
├── data/
│   ├── repository/
│   │   └── AuthRepositoryImpl.kt  # Implementación con Firebase
│   └── mapper/
│       └── AuthMapper.kt          # Firebase User → AuthUser
├── ui/
│   ├── login/
│   │   ├── LoginScreen.kt
│   │   └── LoginViewModel.kt
│   ├── register/
│   │   ├── RegisterScreen.kt
│   │   └── RegisterViewModel.kt
│   ├─��� onboarding/
│   │   ├── OnboardingScreen.kt
│   │   └── OnboardingViewModel.kt
│   └── navigation/
│       └── AuthNavigation.kt
└── di/
    └── AuthModule.kt              # Hilt module
```

## 🔐 Firebase Auth Integration

### Métodos de Autenticación

**Email/Password:**

```kotlin
val authResult = authRepository.login(
    LoginRequest(
        email = "user@example.com",
        password = "password123"
    )
)
```

**Google Sign-In:**

```kotlin
val authResult = authRepository.loginWithGoogle()
```

**Phone (SMS):**

```kotlin
// Usando FirebaseUI
val signInIntent = AuthUI.getInstance()
    .createSignInIntentBuilder()
    .setAvailableProviders(listOf(
        AuthUI.IdpConfig.PhoneBuilder().build()
    ))
    .build()
```

## 📝 Flujo de Autenticación

### 1. Primera Vez (Registro)

```
SplashScreen
    ↓
OnboardingScreen (Bienvenida)
    ↓
User selecciona: "Crear Cuenta"
    ↓
RegisterScreen
    ├─ Email/Password
    ├─ Google
    └─ Phone
    ↓
OnboardingProfileScreen
    ├─ Seleccionar tipo (Collector/Store)
    ├─ Nombre de usuario
    ├─ Display name
    └─ Aceptar términos
    ↓
Crear perfil en Firebase
    ↓
MainActivity (Home)
```

### 2. Usuario Existente (Login)

```
SplashScreen
    ↓
Check if logged in?
    ├─ Yes → MainActivity
    └─ No ↓
LoginScreen
    ├─ Email/Password
    ├─ Google (1-tap)
    └─ Phone
    ↓
MainActivity (Home)
```

### 3. Recuperación de Contraseña

```
LoginScreen
    ↓
"Forgot Password?"
    ↓
ForgotPasswordScreen
    ├─ Ingresar email
    └─ Enviar link de reset
    ↓
Check email
    ↓
Reset password en web
    ↓
LoginScreen
```

## 🎨 Screens

### LoginScreen

```kotlin
@Composable
fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit
) {
    Column {
        // Logo
        Image(...)
        
        // Email field
        OutlinedTextField(
            value = state.email,
            onValueChange = { onAction(LoginAction.EmailChanged(it)) }
        )
        
        // Password field
        OutlinedTextField(
            value = state.password,
            isPassword = true,
            onValueChange = { onAction(LoginAction.PasswordChanged(it)) }
        )
        
        // Login button
        Button(
            onClick = { onAction(LoginAction.LoginClicked) },
            enabled = state.isValid && !state.isLoading
        ) {
            Text("Login")
        }
        
        // Or divider
        Divider()
        
        // Google login
        GoogleSignInButton(
            onClick = { onAction(LoginAction.GoogleLoginClicked) }
        )
        
        // Register link
        TextButton(
            onClick = { onAction(LoginAction.RegisterClicked) }
        ) {
            Text("Don't have an account? Register")
        }
        
        // Forgot password
        TextButton(
            onClick = { onAction(LoginAction.ForgotPasswordClicked) }
        ) {
            Text("Forgot password?")
        }
    }
}
```

### RegisterScreen

```kotlin
@Composable
fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit
) {
    Column {
        // Email
        OutlinedTextField(...)
        
        // Password
        OutlinedTextField(
            isPassword = true,
            supportingText = {
                PasswordStrengthIndicator(state.passwordStrength)
            }
        )
        
        // Confirm password
        OutlinedTextField(...)
        
        // Display name
        OutlinedTextField(...)
        
        // Username
        OutlinedTextField(
            supportingText = {
                if (state.isCheckingUsername) {
                    Text("Checking availability...")
                } else if (state.usernameAvailable) {
                    Text("✓ Available", color = Green)
                } else {
                    Text("✗ Already taken", color = Red)
                }
            }
        )
        
        // User type selection
        SegmentedButton {
            Button("Collector")
            Button("Store")
        }
        
        // Terms checkbox
        Row {
            Checkbox(...)
            Text("I accept terms and conditions")
        }
        
        // Register button
        Button(
            onClick = { onAction(RegisterAction.RegisterClicked) },
            enabled = state.isValid && !state.isLoading
        ) {
            Text("Create Account")
        }
    }
}
```

### OnboardingProfileScreen

```kotlin
@Composable
fun OnboardingProfileScreen(
    state: OnboardingState,
    onAction: (OnboardingAction) -> Unit
) {
    Column {
        Text("Complete your profile")
        
        // Avatar picker
        AvatarPicker(
            currentUrl = state.photoUrl,
            onPick = { onAction(OnboardingAction.PhotoSelected(it)) }
        )
        
        // Bio
        OutlinedTextField(
            label = "Bio",
            maxLines = 3
        )
        
        // Location (optional)
        OutlinedTextField(
            label = "Location"
        )
        
        // Complete button
        Button(
            onClick = { onAction(OnboardingAction.CompleteClicked) }
        ) {
            Text("Get Started")
        }
        
        // Skip button
        TextButton(
            onClick = { onAction(OnboardingAction.SkipClicked) }
        ) {
            Text("Skip for now")
        }
    }
}
```

## 🔄 ViewModels

### LoginViewModel

```kotlin
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : MviViewModel<LoginState, LoginEffect, LoginAction>() {
    
    override fun handleAction(action: LoginAction) = when (action) {
        is LoginAction.EmailChanged -> updateEmail(action.email)
        is LoginAction.PasswordChanged -> updatePassword(action.password)
        LoginAction.LoginClicked -> login()
        LoginAction.GoogleLoginClicked -> loginWithGoogle()
        LoginAction.RegisterClicked -> navigateToRegister()
        LoginAction.ForgotPasswordClicked -> navigateToForgotPassword()
    }
    
    private fun login() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            
            val result = loginUseCase(
                LoginRequest(
                    email = state.value.email,
                    password = state.value.password
                )
            )
            
            when {
                result.success -> {
                    _effects += LoginEffect.NavigateToHome
                }
                result.error != null -> {
                    _state.update { 
                        it.copy(
                            isLoading = false,
                            error = result.error.message
                        )
                    }
                }
            }
        }
    }
}
```

## 🗄️ Repository Implementation

```kotlin
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDb: FirebaseDatabaseService
) : AuthRepository {
    
    override fun getCurrentUser(): Flow<AuthUser?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser?.toAuthUser())
        }
        firebaseAuth.addAuthStateListener(listener)
        awaitClose { firebaseAuth.removeAuthStateListener(listener) }
    }
    
    override suspend fun login(request: LoginRequest): AuthResult {
        return try {
            val result = firebaseAuth
                .signInWithEmailAndPassword(request.email, request.password)
                .await()
            
            AuthResult(
                success = true,
                user = result.user?.toAuthUser()
            )
        } catch (e: FirebaseAuthException) {
            AuthResult(
                success = false,
                error = e.toAuthError()
            )
        }
    }
    
    override suspend fun register(request: RegisterRequest): AuthResult {
        return try {
            // 1. Create auth user
            val result = firebaseAuth
                .createUserWithEmailAndPassword(request.email, request.password)
                .await()
            
            val user = result.user ?: return AuthResult(
                success = false,
                error = AuthError.Unknown("User creation failed")
            )
            
            // 2. Update display name
            user.updateProfile(
                UserProfileChangeRequest.Builder()
                    .setDisplayName(request.displayName)
                    .build()
            ).await()
            
            // 3. Create user profile in Realtime DB
            val userProfile = UserProfile(
                uid = user.uid,
                email = request.email,
                displayName = request.displayName,
                username = request.username,
                userType = request.userType,
                createdAt = System.currentTimeMillis()
            )
            
            firebaseDb.writeData("users/${user.uid}/profile", userProfile)
            
            // 4. Send verification email
            user.sendEmailVerification().await()
            
            AuthResult(
                success = true,
                user = user.toAuthUser()
            )
        } catch (e: Exception) {
            AuthResult(
                success = false,
                error = e.toAuthError()
            )
        }
    }
}
```

## ✅ Validaciones

### Email Validation

```kotlin
fun String.isValidEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
```

### Password Validation

```kotlin
data class PasswordStrength(
    val score: Int, // 0-4
    val feedback: String
)

fun String.getPasswordStrength(): PasswordStrength {
    var score = 0
    
    if (length >= 8) score++
    if (any { it.isUpperCase() }) score++
    if (any { it.isLowerCase() }) score++
    if (any { it.isDigit() }) score++
    if (any { !it.isLetterOrDigit() }) score++
    
    return PasswordStrength(
        score = score,
        feedback = when {
            score < 2 -> "Weak"
            score < 4 -> "Medium"
            else -> "Strong"
        }
    )
}
```

### Username Validation

```kotlin
suspend fun isUsernameAvailable(username: String): Boolean {
    val query = firestore
        .collection("users")
        .whereEqualTo("username", username)
        .limit(1)
        .get()
        .await()
    
    return query.isEmpty
}
```

## 🔒 Security Considerations

1. **Never log passwords** - No incluir passwords en logs
2. **Use HTTPS only** - Firebase ya usa HTTPS
3. **Rate limiting** - Firebase Auth tiene rate limiting integrado
4. **Email verification** - Enviar siempre verification email
5. **Strong passwords** - Mínimo 8 caracteres con validación
6. **Session timeout** - Firebase maneja tokens automáticamente

## 🧪 Testing

```kotlin
@Test
fun `login with valid credentials succeeds`() = runTest {
    // Given
    val request = LoginRequest("user@test.com", "password123")
    coEvery { authRepository.login(request) } returns AuthResult(
        success = true,
        user = mockAuthUser
    )
    
    // When
    val result = loginUseCase(request)
    
    // Then
    assertTrue(result.success)
    assertNotNull(result.user)
}
```

## 📊 Estados de Autenticación

```kotlin
sealed class AuthState {
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Authenticated(val user: AuthUser) : AuthState()
    data class NeedsOnboarding(val user: AuthUser) : AuthState()
    data class Error(val error: AuthError) : AuthState()
}
```

## 🚀 Setup

### 1. Habilitar providers en Firebase Console

- Email/Password
- Google
- Phone

### 2. Configurar Google Sign-In

```kotlin
// app/build.gradle.kts
dependencies {
    implementation("com.google.android.gms:play-services-auth:20.7.0")
}
```

### 3. Agregar OAuth client ID

Descargar `google-services.json` actualizado desde Firebase Console

## 📖 Referencias

- [Firebase Auth Documentation](https://firebase.google.com/docs/auth)
- [FirebaseUI Auth](https://github.com/firebase/FirebaseUI-Android)
- [Google Sign-In](https://developers.google.com/identity/sign-in/android)

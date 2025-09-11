package akibaroom.core.firebase.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Example User data class for Firebase Realtime Database
 */
@Parcelize
data class User(
    val uid: String = "",
    val email: String = "",
    val displayName: String = "",
    val photoUrl: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val lastLoginAt: Long = System.currentTimeMillis()
) : Parcelable

/**
 * Example Figure data class for the AkibaRoom app
 */
@Parcelize
data class Figure(
    val id: String = "",
    val name: String = "",
    val series: String = "",
    val manufacturer: String = "",
    val price: Double = 0.0,
    val releaseDate: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val addedAt: Long = System.currentTimeMillis()
) : Parcelable

/**
 * Example Collection data class
 */
@Parcelize
data class Collection(
    val id: String = "",
    val userId: String = "",
    val name: String = "",
    val description: String = "",
    val figures: List<String> = emptyList(), // Figure IDs
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) : Parcelable

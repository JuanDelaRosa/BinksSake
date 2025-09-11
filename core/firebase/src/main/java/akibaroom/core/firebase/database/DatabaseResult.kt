package akibaroom.core.firebase.database

import com.google.firebase.database.DataSnapshot

/**
 * Sealed class representing database operation results
 */
sealed class DatabaseResult<T> {
    data class Success<T>(val data: T) : DatabaseResult<T>()
    data class Error<T>(val exception: Exception) : DatabaseResult<T>()
}

/**
 * Extension function to convert DataSnapshot to DatabaseResult
 */
fun <T> DataSnapshot.toDatabaseResult(mapper: (DataSnapshot) -> T): DatabaseResult<T> {
    return try {
        DatabaseResult.Success(mapper(this))
    } catch (e: Exception) {
        DatabaseResult.Error(e)
    }
}

/**
 * Extension function to get value from DataSnapshot safely
 */
fun <T> DataSnapshot.getValueSafely(clazz: Class<T>): T? {
    return try {
        getValue(clazz)
    } catch (e: Exception) {
        null
    }
}

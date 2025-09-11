package akibaroom.core.firebase.database

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseDatabaseService @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) {
    
    /**
     * Get a reference to a specific path in the database
     */
    fun getReference(path: String): DatabaseReference {
        return firebaseDatabase.getReference(path)
    }
    
    /**
     * Get root reference
     */
    fun getRootReference(): DatabaseReference {
        return firebaseDatabase.reference
    }
    
    /**
     * Write data to a specific path
     */
    suspend fun writeData(path: String, data: Any) {
        getReference(path).setValue(data).await()
    }
    
    /**
     * Update data at a specific path
     */
    suspend fun updateData(path: String, data: Map<String, Any>) {
        getReference(path).updateChildren(data).await()
    }
    
    /**
     * Read data once from a specific path
     */
    suspend fun readData(path: String): DataSnapshot? {
        return getReference(path).get().await()
    }
    
    /**
     * Listen to real-time changes at a specific path
     */
    fun listenToData(path: String): Flow<DataSnapshot?> = callbackFlow {
        val reference = getReference(path)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                trySend(snapshot)
            }
            
            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        
        reference.addValueEventListener(listener)
        
        awaitClose {
            reference.removeEventListener(listener)
        }
    }
    
    /**
     * Listen to real-time changes at a specific path with error handling
     */
    fun listenToDataWithErrorHandling(path: String): Flow<Result<DataSnapshot?>> = callbackFlow {
        val reference = getReference(path)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                trySend(Result.success(snapshot))
            }
            
            override fun onCancelled(error: DatabaseError) {
                trySend(Result.failure(error.toException()))
            }
        }
        
        reference.addValueEventListener(listener)
        
        awaitClose {
            reference.removeEventListener(listener)
        }
    }
    
    /**
     * Push data to a list (generates unique key)
     */
    suspend fun pushData(path: String, data: Any): String? {
        val reference = getReference(path).push()
        reference.setValue(data).await()
        return reference.key
    }
    
    /**
     * Delete data at a specific path
     */
    suspend fun deleteData(path: String) {
        getReference(path).removeValue().await()
    }
    
    /**
     * Check if data exists at a specific path
     */
    suspend fun dataExists(path: String): Boolean {
        val snapshot = readData(path)
        return snapshot?.exists() == true
    }
    
    /**
     * Get child count at a specific path
     */
    suspend fun getChildCount(path: String): Long {
        val snapshot = readData(path)
        return snapshot?.childrenCount ?: 0
    }
    
    /**
     * Query data with ordering
     */
    fun queryOrderedByChild(path: String, childKey: String): com.google.firebase.database.Query {
        return getReference(path).orderByChild(childKey)
    }
    
    /**
     * Query data with value ordering
     */
    fun queryOrderedByValue(path: String): com.google.firebase.database.Query {
        return getReference(path).orderByValue()
    }
    
    /**
     * Query data with key ordering
     */
    fun queryOrderedByKey(path: String): com.google.firebase.database.Query {
        return getReference(path).orderByKey()
    }
    
    /**
     * Set database persistence enabled (call this once in your app)
     */
    fun setPersistenceEnabled(enabled: Boolean) {
        firebaseDatabase.setPersistenceEnabled(enabled)
    }
    
    /**
     * Set offline persistence enabled (call this once in your app)
     */
    fun setOfflinePersistenceEnabled(enabled: Boolean) {
        firebaseDatabase.setPersistenceEnabled(enabled)
    }
}

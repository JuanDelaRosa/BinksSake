package binkssake.core.utils.json

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

inline fun <reified T : Any> safeJsonParse(json: () -> String): Result<T> {
    return try {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter = moshi.adapter(T::class.java)
        val result = adapter.fromJson(json())

        if (result != null) {
            Result.Success(result)
        } else {
            Result.Error(NullPointerException("Parsed object is null"))
        }
    } catch (e: Exception) {
        Result.Error(e)
    }
}

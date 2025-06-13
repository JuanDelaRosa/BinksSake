package binkssake.core.utils.json

import kotlinx.serialization.json.Json

val jsonParser: Json = Json {
    ignoreUnknownKeys = true
    prettyPrint = false
    isLenient = true
}

inline fun <reified T : Any> safeJsonParse(json: () -> String): Result<T> {
    return try {
        val result = jsonParser.decodeFromString<T>(json())
        Result.Success(result)
    } catch (e: Exception) {
        Result.Error(e)
    }
}


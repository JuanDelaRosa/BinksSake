package akibaroom.core.network.models

sealed interface Response<out T : Any> {

    data class Success<out T : Any>(
        val data: T,
    ) : Response<T>

    data class Error(
        val error: CustomError,
    ) : Response<Nothing>
}

inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
    if (this is Response.Success) action(data)
    return this
}

inline fun <T : Any> Response<T>.onError(action: (CustomError) -> Unit) {
    if (this is Response.Error) action(error)
}

@Suppress("NOTHING_TO_INLINE") // Inlining is used to separate Crashlytics events
inline fun <T : Any> Response<T>.getThrowable(): Throwable {
    val error = (this as? Response.Error)?.error ?: CustomError()
    return error.throwable ?: CustomException(
        businessError = error.errorMessage.takeIf { error.isBusinessError },
        message = error.errorMessage,
        httpCode = error.responseCode.takeIf { it != 0 },
    )
}

inline fun <T : Any, R : Any> Response<T>.mapOnSuccess(
    map: (T) -> R
): Response<R> = when (this) {
    is Response.Success -> Response.Success(map(data))
    is Response.Error -> Response.Error(error)
}

fun <T : Any> Response<T>.toResult(): Result<T> = when (this) {
    is Response.Error -> Result.failure(getThrowable())
    is Response.Success -> Result.success(data)
}

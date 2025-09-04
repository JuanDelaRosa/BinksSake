package akibaroom.core.network.models

@Suppress("unused")
object HttpStatusCode {

    const val OK = 200
    const val CREATED = 201
    const val ACCEPTED = 202
    const val NO_CONTENT = 204

    const val MULTIPLE_CHOICES = 300
    const val MOVED_PERMANENTLY = 301
    const val FOUND = 302
    const val SEE_OTHER = 303
    const val NOT_MODIFIED = 304
    const val TEMPORARY_REDIRECT = 307
    const val PERMANENT_REDIRECT = 308

    const val BAD_REQUEST = 400
    const val UNAUTHORIZED = 401
    const val ACCOUNT_SUSPENDED = 402
    const val FORBIDDEN = 403
    const val NOT_FOUND = 404
    const val METHOD_NOT_ALLOWED = 405
    const val CONFLICT = 409
    const val PRECONDITION_FAILED = 412
    const val UNPROCESSABLE_ENTITY = 422

    const val SERVER_ERROR = 500
    const val BAD_GATEWAY = 502
    const val SERVICE_UNAVAILABLE = 503
    const val GATEWAY_TIMEOUT = 504

    fun isSuccess(statusCode: Int): Boolean = statusCode in OK until MULTIPLE_CHOICES

    fun isRedirect(statusCode: Int): Boolean = statusCode in MULTIPLE_CHOICES until BAD_REQUEST

    fun isClientError(statusCode: Int): Boolean = statusCode in BAD_REQUEST until SERVER_ERROR

    fun isServerError(statusCode: Int): Boolean = statusCode >= SERVER_ERROR

    fun isGatewayError(statusCode: Int): Boolean = statusCode == BAD_GATEWAY || statusCode == GATEWAY_TIMEOUT

    fun isUnauthorizedError(statusCode: Int): Boolean = (statusCode == UNAUTHORIZED || statusCode == ACCOUNT_SUSPENDED)
}

package akibaroom.core.network.retrofit

import akibaroom.core.network.R
import akibaroom.core.network.models.CustomError
import akibaroom.core.network.models.CustomError.Companion.createGenericHcError
import akibaroom.core.network.models.HttpStatusCode
import akibaroom.core.network.models.Response
import akibaroom.core.utils.enviroment.AppContextProvider
import kotlinx.coroutines.ensureActive
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.coroutineContext

fun Exception.toErrorResponse(): Response.Error = when {
    isNetworkFailure() -> Response.Error(
        CustomError(
            errorMessage = AppContextProvider.context.getString(R.string.no_network_connection),
            throwable = this
        )
    )
    else -> Response.Error(createGenericHcError(this))
}

@Suppress("TooGenericExceptionCaught")
suspend inline fun <T : Any> safeServiceCall(
    errorHandlingPolicy: ErrorHandlingPolicy = ErrorHandlingPolicy.None,
    crossinline apiCall: suspend () -> retrofit2.Response<T>,
): Response<T> =
    try {
        val serviceResponse = apiCall()
        val data = serviceResponse.body()

        when {
            serviceResponse.code() == HttpStatusCode.NO_CONTENT && data == null -> {
                val emptyBody: T = Unit as T
                Response.Success(data = emptyBody)
            }
            serviceResponse.isSuccessful && data != null -> {
                Response.Success(data = data)
            }
            errorHandlingPolicy.shouldHandle(serviceResponse.code()) && serviceResponse.errorBody() != null -> {
                Response.Error(createGenericHcError())
            }
            HttpStatusCode.isGatewayError(serviceResponse.code()) -> {
                Response.Error(createGenericHcError())
            }
            HttpStatusCode.isUnauthorizedError(serviceResponse.code()) -> {
                Response.Error(createGenericHcError())
            }
            else -> {
                Response.Error(createGenericHcError())
            }
        }
    } catch (ex: Exception) {
        coroutineContext.ensureActive()
        ex.toErrorResponse()
    }

sealed interface ErrorHandlingPolicy {
    data object None : ErrorHandlingPolicy
    data object All : ErrorHandlingPolicy
    data class Selected(val httpStatusCodes: Set<Int>) : ErrorHandlingPolicy {
        constructor(vararg httpStatusCodes: Int) : this(httpStatusCodes.toSet())
    }

    fun shouldHandle(httpStatusCode: Int) =
        this == All || (this is Selected && httpStatusCodes.contains(httpStatusCode))
}

private const val NO_NETWORK_MESSAGE: String = "No network connection"

fun Throwable?.isNetworkFailure() =
    this is SocketTimeoutException ||
            this is UnknownHostException ||
            this is NoRouteToHostException ||
            this is ConnectException ||
            this?.message?.contains(NO_NETWORK_MESSAGE) == true

package akibaroom.core.network.models

import akibaroom.core.network.R
import akibaroom.core.utils.enviroment.AppContextProvider
import okhttp3.ResponseBody
import java.io.Serializable

data class CustomError(
    var responseCode: Int = 0,
    var errorMessage: String = AppContextProvider.context.getString(R.string.error_something_went_wrong),
    var errorCode: String? = null,
    var throwable: Throwable? = null
) : Serializable {

    val isNetworkError get() = errorMessage == AppContextProvider.context.getString(R.string.no_network_connection)
    val isBusinessError
        get() = responseCode != 0 &&
            errorMessage != AppContextProvider.context.getString(R.string.error_something_went_wrong)

    companion object {

        fun createGenericHcError(throwable: Throwable? = null) = CustomError().apply {
            errorMessage = AppContextProvider.context.getString(R.string.error_something_went_wrong)
            this.throwable = throwable
        }
    }
}

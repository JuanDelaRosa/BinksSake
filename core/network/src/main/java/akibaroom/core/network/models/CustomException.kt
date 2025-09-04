package akibaroom.core.network.models

data class CustomException(
    var businessError: String?,
    override val message: String,
    val httpCode: Int? = null,
) : Exception(message)

package akibaroom.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Figure(
    val id: String,
    val name: String,
    val image: String
)

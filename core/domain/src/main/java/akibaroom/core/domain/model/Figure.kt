package akibaroom.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Figure(
    val id: Int,
    val name: String,
    val image: String
)
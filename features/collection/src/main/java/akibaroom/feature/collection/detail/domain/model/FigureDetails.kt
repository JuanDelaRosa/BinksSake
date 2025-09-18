package akibaroom.feature.collection.detail.domain.model

data class FigureDetails(
    val uuid: String,
    val name: String?,
    val description: String?,
    val imageUrl: String?,
    val version: String?,
    val character: Character?,
)

data class Character(
    val uuid: String,
    val imageUrl: String,
    val name: String,
)

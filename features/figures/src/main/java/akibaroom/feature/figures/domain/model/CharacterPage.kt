package akibaroom.feature.figures.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class FigurePage(
    val info: PageInfo,
    val results: List<Figure>
)

@Serializable
data class PageInfo(
    val count: Int,
    val pages: Int
)

@Serializable
data class Figure(
    val id: Int,
    val name: String,
    val image: String
)

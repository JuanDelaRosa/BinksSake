package akibaroom.feature.figures.domain.model

import akibaroom.core.domain.model.Figure
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

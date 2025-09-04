package akibaroom.feature.figures.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class FigurePageResponse(
    val info: PageInfoResponse?,
    val results: List<FigureResponse>?
)

@Serializable
data class PageInfoResponse(
    val count: Int?,
    val pages: Int?
)

@Serializable
data class FigureResponse(
    val id: Int?,
    val name: String?,
    val image: String?
)

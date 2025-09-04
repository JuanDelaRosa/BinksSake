package akibaroom.feature.figures.domain.service

import akibaroom.core.network.models.Response
import akibaroom.feature.figures.data.entity.CharacterPageResponse

interface FigureService {
    suspend fun fetchFigures(
        page: Int
    ): Response<CharacterPageResponse>
}

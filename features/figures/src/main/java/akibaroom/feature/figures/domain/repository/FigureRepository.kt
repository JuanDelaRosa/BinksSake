package akibaroom.feature.figures.domain.repository

import akibaroom.core.network.models.Response
import akibaroom.feature.figures.domain.model.FigurePage

interface FigureRepository {
    suspend fun fetchFigures(
        page: Int
    ): Response<FigurePage>
}

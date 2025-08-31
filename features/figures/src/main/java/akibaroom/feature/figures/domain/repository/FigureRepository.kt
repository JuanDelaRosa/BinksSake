package akibaroom.feature.figures.domain.repository

import akibaroom.core.utils.json.Result
import akibaroom.feature.figures.ui.FigureUi

interface FigureRepository {
    suspend fun fetchFigures(): Result<List<FigureUi>>
}

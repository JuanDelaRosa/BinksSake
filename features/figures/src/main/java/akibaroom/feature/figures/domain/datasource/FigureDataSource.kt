package akibaroom.feature.figures.domain.datasource

import akibaroom.core.utils.json.Result
import akibaroom.feature.figures.ui.FigureUi

interface FigureDataSource {
    suspend fun fetchFigures() : Result<List<FigureUi>>
}

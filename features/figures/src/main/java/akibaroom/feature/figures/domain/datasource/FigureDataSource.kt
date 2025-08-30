package akibaroom.feature.figures.domain.datasource

import akibaroom.core.utils.json.Result
import akibaroom.feature.figures.api.CharacterResponse

interface FigureDataSource {
    suspend fun fetchFigures() : Result<List<CharacterResponse>>
}

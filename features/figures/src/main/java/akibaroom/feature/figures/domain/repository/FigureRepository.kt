package akibaroom.feature.figures.domain.repository

import akibaroom.core.utils.json.Result
import akibaroom.feature.figures.api.CharacterResponse

interface FigureRepository {
    suspend fun fetchFigures(): Result<List<CharacterResponse>>
}

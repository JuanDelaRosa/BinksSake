package akibaroom.feature.figures.data

import akibaroom.feature.figures.api.RickAndMortyApi
import javax.inject.Inject

class FigureDataSource @Inject constructor(
    private val api: RickAndMortyApi
) {
    suspend fun fetchPage(page: Int) = api.getCharacters(page)
    suspend fun fetchById(id: Int) = api.getCharacter(id)
}



package akibaroom.feature.figures.data.service

import akibaroom.core.network.Networking
import akibaroom.core.network.models.Response
import akibaroom.core.network.retrofit.safeServiceCall
import akibaroom.feature.figures.data.api.RickAndMortyApi
import akibaroom.feature.figures.data.entity.CharacterPageResponse
import akibaroom.feature.figures.domain.service.FigureService

class FigureServiceImpl(
    private val api: RickAndMortyApi = Networking.createService(RickAndMortyApi::class.java)
): FigureService {
    override suspend fun fetchFigures(
        page: Int
    ) = safeServiceCall { api.getCharacters(1) }
}

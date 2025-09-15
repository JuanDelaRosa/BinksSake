package akibaroom.feature.collection.discover.domain.repository

import akibaroom.core.domain.model.Figure
import akibaroom.core.network.models.Response
import akibaroom.feature.collection.discover.domain.model.DiscoverSection

interface FigureRepository {
    /*suspend fun fetchFiguresPage(
        page: Int
    ): Response<FigurePage>*/

    suspend fun fetchSections(): Response<List<DiscoverSection>>
    suspend fun fetchFigureDetail(uuid: String): Response<Figure>
    suspend fun searchFigures(query: String): Response<List<Figure>>
}

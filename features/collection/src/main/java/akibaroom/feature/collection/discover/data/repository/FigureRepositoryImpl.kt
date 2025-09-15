package akibaroom.feature.collection.discover.data.repository

import akibaroom.core.domain.model.Figure
import akibaroom.core.network.models.Response
import akibaroom.feature.collection.FiguresMoke
import akibaroom.feature.collection.discover.domain.model.DiscoverSection
import akibaroom.feature.collection.discover.domain.repository.FigureRepository

class FigureRepositoryImpl(
    //private val service: FigureService = FigureServiceImpl(),
    //private val mapper: FigureMapper = FigureMapper()
): FigureRepository {
    override suspend fun fetchSections(): Response<List<DiscoverSection>> {
        return Response.Success(FiguresMoke.sections)
    }

    override suspend fun fetchFigureDetail(uuid: String): Response<Figure> {
        return Response.Success(FiguresMoke.figures.first())
    }

    override suspend fun searchFigures(query: String): Response<List<Figure>> {
        return Response.Success(FiguresMoke.figures)
    }


    /*override suspend fun fetchFigures(page: Int) = when (val response = service.fetchFigures(page)) {
        is Response.Success -> {
            mapper.toModel(response.data)?.let {
                Response.Success(it)
            } ?: Response.Error(CustomError())
        }
        is Response.Error -> {
            response
        }
    }*/
}

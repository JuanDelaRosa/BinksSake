package akibaroom.feature.figures.data.repository

import akibaroom.core.network.models.CustomError
import akibaroom.core.network.models.Response
import akibaroom.feature.figures.data.mapper.FigureMapper
import akibaroom.feature.figures.data.service.FigureServiceImpl
import akibaroom.feature.figures.domain.repository.FigureRepository
import akibaroom.feature.figures.domain.service.FigureService

class FigureRepositoryImpl(
    private val service: FigureService = FigureServiceImpl(),
    private val mapper: FigureMapper = FigureMapper()
): FigureRepository {
    override suspend fun fetchFigures(page: Int) = when (val response = service.fetchFigures(page)) {
        is Response.Success -> {
            mapper.toModel(response.data)?.let {
                Response.Success(it)
            } ?: Response.Error(CustomError())
        }
        is Response.Error -> {
            response
        }
    }
}

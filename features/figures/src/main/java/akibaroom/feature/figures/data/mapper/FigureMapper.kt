package akibaroom.feature.figures.data.mapper

import akibaroom.core.network.DomainModelMapper
import akibaroom.feature.figures.data.entity.FigurePageResponse
import akibaroom.feature.figures.domain.model.Figure
import akibaroom.feature.figures.domain.model.FigurePage
import akibaroom.feature.figures.domain.model.PageInfo

class FigureMapper: DomainModelMapper<FigurePageResponse, FigurePage?> {
    override fun toModel(entity: FigurePageResponse): FigurePage? {
        if (entity.info == null || entity.results == null) {
            return null
        }
        return FigurePage(
            info = PageInfo(
                count = entity.info.count ?: 0,
                pages = entity.info.pages ?: 0
            ),
            results = entity.results.map {
                Figure(
                    id = it.id ?: 0,
                    name = it.name ?: "",
                    image = it.image ?: ""
                )
            }
        )
    }

}

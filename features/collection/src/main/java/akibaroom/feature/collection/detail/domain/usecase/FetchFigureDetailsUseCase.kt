package akibaroom.feature.collection.detail.domain.usecase

import akibaroom.feature.collection.discover.domain.repository.FigureRepository
import javax.inject.Inject

class FetchFigureDetailsUseCase @Inject constructor(
    private val figureRepository: FigureRepository
) {
    suspend operator fun invoke(uuid: String) = figureRepository.fetchFigureDetail(uuid)
}

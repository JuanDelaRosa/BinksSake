package akibaroom.feature.figures.domain.usecase

import akibaroom.core.utils.coroutines.DefaultDispatchersProvider
import akibaroom.core.utils.coroutines.DispatchersProvider
import akibaroom.feature.figures.domain.repository.FigureRepository
import kotlinx.coroutines.withContext

class FetchFigureUseCase(
    private val repository: FigureRepository,
    private val dispatcher: DispatchersProvider = DefaultDispatchersProvider()
) {
    suspend operator fun invoke() =
        withContext(dispatcher.io) {
            repository.fetchFigures()
        }
}

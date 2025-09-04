package akibaroom.feature.figures.domain.usecase

import akibaroom.core.utils.coroutines.DefaultDispatchersProvider
import akibaroom.core.utils.coroutines.DispatchersProvider
import akibaroom.feature.figures.data.repository.FigureRepositoryImpl
import akibaroom.feature.figures.domain.repository.FigureRepository
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class FetchFigureUseCase(
    private val figureRepository: FigureRepository = FigureRepositoryImpl(),
    private val dispatcher: DispatchersProvider = DefaultDispatchersProvider()
) {
    suspend operator fun invoke(page: Int) = withContext(dispatcher.io) {
        figureRepository.fetchFigures(page)
    }
}

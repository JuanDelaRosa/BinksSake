package akibaroom.feature.figures.domain.usecase

import akibaroom.core.utils.coroutines.DefaultDispatchersProvider
import akibaroom.core.utils.coroutines.DispatchersProvider
import akibaroom.feature.figures.data.repository.SakeShopsRepositoryImpl
import akibaroom.feature.figures.domain.repository.SakeShopsRepository
import kotlinx.coroutines.withContext

class FetchSakeShopsUseCase(
    private val repository: SakeShopsRepository = SakeShopsRepositoryImpl(),
    private val dispatcher: DispatchersProvider = DefaultDispatchersProvider()
) {
    suspend operator fun invoke() =
        withContext(dispatcher.io) {
            repository.fetchSakeShops()
        }
}

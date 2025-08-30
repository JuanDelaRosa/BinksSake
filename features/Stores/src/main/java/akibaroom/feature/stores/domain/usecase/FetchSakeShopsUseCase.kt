package akibaroom.feature.stores.domain.usecase

import akibaroom.core.utils.coroutines.DefaultDispatchersProvider
import akibaroom.core.utils.coroutines.DispatchersProvider
import akibaroom.feature.stores.data.repository.SakeShopsRepositoryImpl
import akibaroom.feature.stores.domain.repository.SakeShopsRepository
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

package binkssake.feature.stores.domain.usecase

import binkssake.core.utils.coroutines.DefaultDispatchersProvider
import binkssake.core.utils.coroutines.DispatchersProvider
import binkssake.feature.stores.data.repository.SakeShopsRepositoryImpl
import binkssake.feature.stores.domain.repository.SakeShopsRepository
import kotlinx.coroutines.withContext

internal class FetchSakeShopsUseCase(
    private val repository: SakeShopsRepository = SakeShopsRepositoryImpl(),
    private val dispatcher: DispatchersProvider = DefaultDispatchersProvider()
) {
    suspend operator fun invoke() {
        withContext(dispatcher.io) {
            repository.fetchSakeShops()
        }
    }
}

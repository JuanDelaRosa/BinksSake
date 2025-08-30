package akibaroom.stores.di

import akibaroom.feature.stores.api.StoresApi
import akibaroom.feature.stores.di.StoresApiImpl
import akibaroom.feature.stores.domain.usecase.FetchSakeShopsUseCase
import akibaroom.feature.stores.domain.repository.SakeShopsRepository
import akibaroom.feature.stores.data.repository.SakeShopsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoresModule {

    @Provides
    @Singleton
    fun provideStoresApi(): StoresApi = StoresApiImpl()

    @Provides
    @Singleton
    fun provideSakeShopsRepository(): SakeShopsRepository = SakeShopsRepositoryImpl()

    @Provides
    @Singleton
    fun provideFetchSakeShopsUseCase(
        repository: SakeShopsRepository
    ): FetchSakeShopsUseCase = FetchSakeShopsUseCase(repository = repository)
}

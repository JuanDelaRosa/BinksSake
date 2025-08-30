package akibaroom.feature.figures.di

import akibaroom.feature.figures.api.StoresApi
import akibaroom.feature.figures.di.StoresApiImpl
import akibaroom.feature.figures.domain.usecase.FetchSakeShopsUseCase
import akibaroom.feature.figures.domain.repository.SakeShopsRepository
import akibaroom.feature.figures.data.repository.SakeShopsRepositoryImpl
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



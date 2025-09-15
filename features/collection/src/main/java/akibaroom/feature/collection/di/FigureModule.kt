package akibaroom.feature.collection.di

import akibaroom.feature.collection.discover.data.repository.FigureRepositoryImpl
import akibaroom.feature.collection.discover.domain.repository.FigureRepository
import akibaroom.feature.collection.discover.domain.usecase.FetchSectionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FigureModule {

    @Provides
    @Singleton
    fun provideFigureRepository(): FigureRepository = FigureRepositoryImpl()

    @Provides
    @Singleton
    fun provideFetchFigureUseCase(repository: FigureRepository): FetchSectionsUseCase =
        FetchSectionsUseCase(figureRepository = repository)
}

package akibaroom.feature.figures.di

import akibaroom.feature.figures.domain.repository.FigureRepository
import akibaroom.feature.figures.data.repository.FigureRepositoryImpl
import akibaroom.feature.figures.domain.usecase.FetchFigureUseCase
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
    fun provideFetchFigureUseCase(repository: FigureRepository): FetchFigureUseCase =
        FetchFigureUseCase(figureRepository = repository)
}



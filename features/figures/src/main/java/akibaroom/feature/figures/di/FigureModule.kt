package akibaroom.feature.figures.di

import akibaroom.feature.figures.domain.datasource.FigureDataSource
import akibaroom.feature.figures.data.datasource.FigureDataSourceImpl
import akibaroom.feature.figures.domain.usecase.FetchFigureUseCase
import akibaroom.feature.figures.domain.repository.FigureRepository
import akibaroom.feature.figures.data.repository.FigureRepositoryImpl
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
    fun provideFigureDataSource(ds: FigureDataSourceImpl): FigureDataSource = ds

    @Provides
    @Singleton
    fun provideFigureRepository(dataSource: FigureDataSource): FigureRepository = FigureRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideFetchFigureUseCase(
        repository: FigureRepository
    ): FetchFigureUseCase = FetchFigureUseCase(repository = repository)
}



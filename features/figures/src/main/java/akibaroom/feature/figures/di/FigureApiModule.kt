package akibaroom.feature.figures.di

import akibaroom.feature.figures.api.FiguresApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FigureApiModule {

    @Binds
    @Singleton
    abstract fun bindFiguresApi(impl: FigureApiImpl): FiguresApi
}



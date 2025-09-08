package akibaroom.feature.collection.di

import akibaroom.feature.collection.api.CollectionApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CollectionApiModule {

    @Binds
    @Singleton
    abstract fun bindFiguresApi(impl: CollectionApiImpl): CollectionApi
}

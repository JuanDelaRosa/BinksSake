package akibaroom.feature.store.di

import akibaroom.feature.store.api.StoreApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StoreApiModule {

    @Binds
    @Singleton
    abstract fun bindFiguresApi(impl: StoreApiImpl): StoreApi
}

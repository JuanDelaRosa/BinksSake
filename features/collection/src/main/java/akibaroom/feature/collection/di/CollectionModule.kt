package akibaroom.feature.collection.di

import akibaroom.feature.collection.collection.data.repository.CollectionRepositoryImpl
import akibaroom.feature.collection.collection.domain.repository.CollectionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CollectionModule {

    @Provides
    @Singleton
    fun provideCollectionRepository(): CollectionRepository {
        return CollectionRepositoryImpl()
    }
}

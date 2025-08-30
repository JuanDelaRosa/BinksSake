package akibaroom.stores.di

import akibaroom.feature.stores.api.StoresApi
import akibaroom.feature.stores.di.StoresApiImpl
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
}

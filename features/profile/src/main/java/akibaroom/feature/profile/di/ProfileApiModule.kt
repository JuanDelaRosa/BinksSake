package akibaroom.feature.profile.di

import akibaroom.features.profile.api.ProfileApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileApiModule {

    @Binds
    @Singleton
    abstract fun bindFiguresApi(impl: ProfileApiImpl): ProfileApi
}

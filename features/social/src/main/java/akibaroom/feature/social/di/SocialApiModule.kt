package akibaroom.feature.social.di

import akibaroom.feature.social.api.SocialApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SocialApiModule {

    @Binds
    @Singleton
    abstract fun bindFiguresApi(impl: SocialApiImpl): SocialApi
}

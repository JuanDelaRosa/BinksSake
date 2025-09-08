package akibaroom.feature.auth.di

import akibaroom.feature.auth.api.AuthApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthApiModule {

    @Binds
    @Singleton
    abstract fun bindFiguresApi(impl: AuthApiImpl): AuthApi
}

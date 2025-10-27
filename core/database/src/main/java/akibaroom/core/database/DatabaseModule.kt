package akibaroom.core.database

import android.content.Context
import androidx.room.Room
import akibaroom.core.database.dao.FiguresDao
import akibaroom.core.database.dao.RemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CollectorDatabase =
        Room.databaseBuilder(context, CollectorDatabase::class.java, "collector.db").build()

    @Provides
    fun provideFiguresDao(db: CollectorDatabase): FiguresDao = db.figuresDao()

    @Provides
    fun provideRemoteKeysDao(db: CollectorDatabase): RemoteKeysDao = db.remoteKeysDao()
}



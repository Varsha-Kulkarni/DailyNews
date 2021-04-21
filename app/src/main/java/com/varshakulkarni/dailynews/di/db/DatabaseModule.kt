package com.varshakulkarni.dailynews.di.db

import android.content.Context
import androidx.room.Room
import com.varshakulkarni.dailynews.data.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabaseInstance(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NewsDatabase::class.java,
            "newsdatabase.db"
        ).fallbackToDestructiveMigration().build()
    }
}
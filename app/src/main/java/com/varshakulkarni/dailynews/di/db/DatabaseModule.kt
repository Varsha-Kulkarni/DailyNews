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

/**
 * Hilt module to provide injections for Room dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    /**
     * Provides the single shared instance for all the Room database operations.
     * This requires `applicationContext` provided by Android system which gives access to
     * Application specific resources. Hilt provides this dependency when annotated with
     * `ApplicationContext`
     * If Migrations are not provided, the Database is recreated
     */
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
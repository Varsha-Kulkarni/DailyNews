package com.varshakulkarni.dailynews.di.datasource

import com.varshakulkarni.dailynews.data.NewsDataSource
import com.varshakulkarni.dailynews.data.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *  provides dependency for DataSource interface
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsNewsDataSource(newsRepository: NewsRepository): NewsDataSource
}
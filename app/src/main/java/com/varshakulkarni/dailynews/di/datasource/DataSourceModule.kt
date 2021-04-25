package com.varshakulkarni.dailynews.di

import com.varshakulkarni.dailynews.data.NewsDataSource
import com.varshakulkarni.dailynews.data.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsNewsDataSource(newsRepository: NewsRepository): NewsDataSource
}
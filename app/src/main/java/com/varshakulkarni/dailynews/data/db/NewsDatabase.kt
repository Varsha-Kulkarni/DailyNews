package com.varshakulkarni.dailynews.data.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import com.varshakulkarni.dailynews.domain.NewsSource
import com.varshakulkarni.dailynews.domain.TopHeadline
import kotlinx.coroutines.flow.Flow

@Database(entities = [TopHeadline::class, NewsSource::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract val topHeadlineDao: TopHeadlineDao
    abstract val newsSourceDao: NewsSourceDao
}

@Dao
interface TopHeadlineDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg topHeadline: TopHeadline): List<Long>

    @Query("delete from top_headlines")
    suspend fun clear()

    @Query("Select * from top_headlines")
    fun getAllTopHeadlines(): Flow<List<TopHeadline>>

    @Query("Select * from top_headlines where isAddedToReadingList=1")
    fun getReadingList(): Flow<List<TopHeadline>>

    @Query("Update top_headlines set isAddedToReadingList=1 where title = :title")
    suspend fun addToReadingList(title: String): Int

    @Query("Update top_headlines set isAddedToReadingList=0 where title = :title")
    suspend fun removeFromReadingList(title: String): Int
}

@Dao
interface NewsSourceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg newsSource: NewsSource): List<Long>

    @Query("delete from news_sources")
    suspend fun clear()

    @Query("Select * from news_sources")
    fun getAllNewsSources(): Flow<List<NewsSource>>
}
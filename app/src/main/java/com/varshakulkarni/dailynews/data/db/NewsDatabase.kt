package com.varshakulkarni.dailynews.data.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg topHeadline: TopHeadline)

    @Query("delete from top_headlines")
    suspend fun clear()

    @Query("Select * from top_headlines")
    fun getAllTopHeadlines(): Flow<List<TopHeadline>>

    @Query("Select * from top_headlines where addToReadingList=1")
    fun getReadlingList(): Flow<List<TopHeadline>>

    @Update
    fun updateReadingList(topHeadlineEntity: TopHeadline)
}

@Dao
interface NewsSourceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg newsSource: NewsSource)

    @Query("delete from news_sources")
    suspend fun clear()

    @Query("Select * from news_sources")
    fun getAllNewsSources(): Flow<List<NewsSource>>
}
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

/**
 *  Room database class achieve the News data persistence
 *
 */

@Database(entities = [TopHeadline::class, NewsSource::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract val topHeadlineDao: TopHeadlineDao
    abstract val newsSourceDao: NewsSourceDao
}

/**
 * DAO declares the methods to
 * insert all the Top Headlines from [News API](https://newsapi.org) and return the list
 * inserted of IDs. If duplicated ignore.
 *
 * clear database table when News become old
 *
 * get all top headlines saved in the top_headlines table based on published date
 * in descending order; emits all the TopHeadline objects
 *
 * get all the top headlines saved for reading later based on boolean flag isAddedToReadingList
 * based on published date in descending order; emits all the TopHeadline objects
 *
 * add to the reading list to read top headlines later,
 * returns the ID of the TopHeadline which is updated
 *
 * remove saved top headline saved for reading later
 * returns the ID of the TopHeadline which is updated
 */
@Dao
interface TopHeadlineDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg topHeadline: TopHeadline): List<Long>

    @Query("delete from top_headlines")
    suspend fun clear()

    @Query("select * from top_headlines order by publishedAt desc")
    fun getAllTopHeadlines(): Flow<List<TopHeadline>>

    @Query("select * from top_headlines where isAddedToReadingList=1 order by publishedAt desc")
    fun getReadingList(): Flow<List<TopHeadline>>

    @Query("update top_headlines set isAddedToReadingList=1 where title = :title")
    suspend fun addToReadingList(title: String): Int

    @Query("update top_headlines set isAddedToReadingList=0 where title = :title")
    suspend fun removeFromReadingList(title: String): Int
}

/**
 * DAO declares the methods to
 * insert all the News Sources from [News API](https://newsapi.org) and return the list
 * inserted of IDs. If duplicated ignore.
 *
 * get all news sources saved in the news_sources table based, emits all the NewsSource objects
 *
 */
@Dao
interface NewsSourceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg newsSource: NewsSource): List<Long>

    @Query("delete from news_sources")
    suspend fun clear()

    @Query("select * from news_sources")
    fun getAllNewsSources(): Flow<List<NewsSource>>
}
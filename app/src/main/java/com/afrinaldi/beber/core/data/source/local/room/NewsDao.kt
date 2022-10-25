package com.afrinaldi.beber.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.afrinaldi.beber.core.data.source.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM news where category = 'Breaking'")
    fun getBreakingNews(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news where category = 'sports'")
    fun getSportNews(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news where category = 'technology'")
    fun getTechNews(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news where category = 'business'")
    fun getBusinessNews(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news where category = 'health'")
    fun getHealthNews(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news where isBookmark = 1")
    fun getBookmarkNews(): Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(news: List<NewsEntity>)

    @Update
    fun updateBookmarkNews(news: NewsEntity)
}
package com.afrinaldi.core.data.source.local

import com.afrinaldi.core.data.source.local.entity.NewsEntity
import com.afrinaldi.core.data.source.local.room.NewsDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val newsDao: NewsDao) {
    fun getBreakingNews(): Flow<List<NewsEntity>> = newsDao.getBreakingNews()

    fun getSportNews(): Flow<List<NewsEntity>> = newsDao.getSportNews()

    fun getTechNews(): Flow<List<NewsEntity>> = newsDao.getTechNews()

    fun getBusinessNews(): Flow<List<NewsEntity>> = newsDao.getBusinessNews()

    fun getHealthNews(): Flow<List<NewsEntity>> = newsDao.getHealthNews()

    fun getBookmarkNews(): Flow<List<NewsEntity>> = newsDao.getBookmarkNews()

    suspend fun deleteNews() = newsDao.deleteNews()

    suspend fun insertNews(newsList: List<NewsEntity>) = newsDao.insertNews(newsList)

    fun setBookmarkNews(news: NewsEntity, isBookmark: Boolean) {
        news.isBookmark = isBookmark
        newsDao.updateBookmarkNews(news)
    }
}
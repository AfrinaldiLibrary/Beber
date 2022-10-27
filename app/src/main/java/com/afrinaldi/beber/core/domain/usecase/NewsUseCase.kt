package com.afrinaldi.beber.core.domain.usecase

import com.afrinaldi.beber.core.data.Resource
import com.afrinaldi.beber.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun getAllNews() : Flow<Resource<List<News>>>

    fun getBookmarkNews(): Flow<List<News>>

    fun setBookmarkNews(news: News, isBookmark: Boolean)

    fun getSportNews() : Flow<Resource<List<News>>>

    fun getTechNews() : Flow<Resource<List<News>>>

    fun getBusinessNews() : Flow<Resource<List<News>>>

    fun getHealthNews() : Flow<Resource<List<News>>>

    suspend fun deleteNews()
}
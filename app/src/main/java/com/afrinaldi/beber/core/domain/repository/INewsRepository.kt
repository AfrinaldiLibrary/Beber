package com.afrinaldi.beber.core.domain.repository

import com.afrinaldi.beber.core.data.Resource
import com.afrinaldi.beber.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    fun getAllNews() : Flow<Resource<List<News>>>

    fun getFavoriteNews(): Flow<List<News>>

    fun setFavoriteNews(news: News, isFavorite: Boolean)
}
package com.afrinaldi.beber.core.domain.usecase

import com.afrinaldi.beber.core.data.Resource
import com.afrinaldi.beber.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun getAllNews() : Flow<Resource<List<News>>>
    fun getFavoriteNews(): Flow<List<News>>
    fun setFavoriteNews(news: News, isFavorite: Boolean)
}
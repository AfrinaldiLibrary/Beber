package com.afrinaldi.beber.core.domain.usecase

import com.afrinaldi.beber.core.domain.model.News
import com.afrinaldi.beber.core.domain.repository.INewsRepository

class NewsInteractor(private val newsRepository: INewsRepository) : NewsUseCase {
    override fun getAllNews() = newsRepository.getAllNews()

    override fun getFavoriteNews() = newsRepository.getFavoriteNews()

    override fun setFavoriteNews(news: News, isFavorite: Boolean) =
        newsRepository.setFavoriteNews(news, isFavorite)

}
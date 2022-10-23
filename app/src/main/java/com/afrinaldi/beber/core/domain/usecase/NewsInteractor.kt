package com.afrinaldi.beber.core.domain.usecase

import com.afrinaldi.beber.core.domain.model.News
import com.afrinaldi.beber.core.domain.repository.INewsRepository

class NewsInteractor(private val newsRepository: INewsRepository) : NewsUseCase {
    override fun getAllNews() = newsRepository.getAllNews()

    override fun getBookmarkNews() = newsRepository.getBookmarkNews()

    override fun setBookmarkNews(news: News, isBookmark: Boolean) =
        newsRepository.setBookmarkNews(news, isBookmark)

}
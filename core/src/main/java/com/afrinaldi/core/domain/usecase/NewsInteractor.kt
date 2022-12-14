package com.afrinaldi.core.domain.usecase

import com.afrinaldi.core.domain.model.News
import com.afrinaldi.core.domain.repository.INewsRepository

class NewsInteractor(private val newsRepository: INewsRepository) : NewsUseCase {
    override fun getAllNews() = newsRepository.getAllNews()

    override fun getBookmarkNews() = newsRepository.getBookmarkNews()

    override fun setBookmarkNews(news: News, isBookmark: Boolean) =
        newsRepository.setBookmarkNews(news, isBookmark)

    override fun getSportNews() = newsRepository.getSportNews()

    override fun getTechNews() = newsRepository.getTechNews()

    override fun getBusinessNews() = newsRepository.getBusinessNews()

    override fun getHealthNews() = newsRepository.getHealthNews()

    override suspend fun deleteNews() = newsRepository.deleteNews()

}
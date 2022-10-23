package com.afrinaldi.beber.presentation.detail

import androidx.lifecycle.ViewModel
import com.afrinaldi.beber.core.domain.model.News
import com.afrinaldi.beber.core.domain.usecase.NewsUseCase

class DetailViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {
    fun setBookmarkTourism(news: News, newStatus: Boolean) =
        newsUseCase.setBookmarkNews(news, newStatus)
}
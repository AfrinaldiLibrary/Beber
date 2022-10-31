package com.afrinaldi.beber.presentation.detail

import androidx.lifecycle.ViewModel
import com.afrinaldi.core.domain.model.News
import com.afrinaldi.core.domain.usecase.NewsUseCase

class DetailViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {
    fun setBookmarkTourism(news: News, newStatus: Boolean) =
        newsUseCase.setBookmarkNews(news, newStatus)
}
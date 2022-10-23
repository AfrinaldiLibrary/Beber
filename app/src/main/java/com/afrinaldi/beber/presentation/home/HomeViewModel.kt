package com.afrinaldi.beber.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.afrinaldi.beber.core.domain.model.News
import com.afrinaldi.beber.core.domain.usecase.NewsUseCase

class HomeViewModel(newsUseCase: NewsUseCase) : ViewModel() {
    val news = newsUseCase.getAllNews().asLiveData()
}
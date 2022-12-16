package com.afrinaldi.beber.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.afrinaldi.core.domain.usecase.NewsUseCase

class HomeViewModel(newsUseCase: NewsUseCase) : ViewModel() {
    val news = newsUseCase.getAllNews().asLiveData()

    val sportNews = newsUseCase.getSportNews().asLiveData()

    val techNews = newsUseCase.getTechNews().asLiveData()

    val businessNews = newsUseCase.getBusinessNews().asLiveData()

    val healthNews = newsUseCase.getHealthNews().asLiveData()

}
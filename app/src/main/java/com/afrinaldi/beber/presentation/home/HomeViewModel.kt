package com.afrinaldi.beber.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.afrinaldi.beber.core.domain.model.News
import com.afrinaldi.beber.core.domain.usecase.NewsUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {
    val news = newsUseCase.getAllNews().asLiveData()

    val sportNews = newsUseCase.getSportNews().asLiveData()

    val techNews = newsUseCase.getTechNews().asLiveData()

    val businessNews = newsUseCase.getBusinessNews().asLiveData()

    val healthNews = newsUseCase.getHealthNews().asLiveData()

    fun deleteNews() {
        viewModelScope.launch {
            newsUseCase.deleteNews()
        }
    }
}
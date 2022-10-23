package com.afrinaldi.beber.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.afrinaldi.beber.core.domain.usecase.NewsUseCase

class BookmarkViewModel(userCase: NewsUseCase) : ViewModel() {
    val bookmarkNews = userCase.getBookmarkNews().asLiveData()
}
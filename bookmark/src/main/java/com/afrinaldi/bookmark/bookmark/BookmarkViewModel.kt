package com.afrinaldi.bookmark.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.afrinaldi.core.domain.usecase.NewsUseCase

class BookmarkViewModel(userCase: NewsUseCase) : ViewModel() {
    val bookmarkNews = userCase.getBookmarkNews().asLiveData()
}
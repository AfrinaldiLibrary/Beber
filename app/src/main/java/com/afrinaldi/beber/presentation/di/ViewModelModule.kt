package com.afrinaldi.beber.presentation.di

import com.afrinaldi.beber.presentation.bookmark.BookmarkViewModel
import com.afrinaldi.beber.presentation.detail.DetailViewModel
import com.afrinaldi.beber.presentation.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { BookmarkViewModel(get()) }
}
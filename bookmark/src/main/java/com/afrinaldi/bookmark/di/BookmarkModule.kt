package com.afrinaldi.bookmark.di

import com.afrinaldi.bookmark.bookmark.BookmarkViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookmarkModule = module {
    viewModel { BookmarkViewModel(get()) }
}
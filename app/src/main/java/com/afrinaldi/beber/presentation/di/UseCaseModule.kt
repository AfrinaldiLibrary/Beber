package com.afrinaldi.beber.presentation.di

import com.afrinaldi.beber.core.domain.usecase.NewsInteractor
import com.afrinaldi.beber.core.domain.usecase.NewsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<NewsUseCase> { NewsInteractor(get()) }
}
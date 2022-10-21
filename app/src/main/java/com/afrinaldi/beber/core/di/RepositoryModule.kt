package com.afrinaldi.beber.core.di

import com.afrinaldi.beber.core.data.NewsRepository
import com.afrinaldi.beber.core.data.source.local.LocalDataSource
import com.afrinaldi.beber.core.data.source.remote.RemoteDataSource
import com.afrinaldi.beber.core.domain.repository.INewsRepository
import com.afrinaldi.beber.core.utils.AppExecutors
import org.koin.dsl.module

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<INewsRepository> { NewsRepository(get(), get(), get()) }
}
package com.afrinaldi.core.di

import com.afrinaldi.core.data.NewsRepository
import com.afrinaldi.core.data.source.local.LocalDataSource
import com.afrinaldi.core.data.source.remote.RemoteDataSource
import com.afrinaldi.core.domain.repository.INewsRepository
import com.afrinaldi.core.utils.AppExecutors
import org.koin.dsl.module

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<INewsRepository> { NewsRepository(get(), get(), get()) }
}
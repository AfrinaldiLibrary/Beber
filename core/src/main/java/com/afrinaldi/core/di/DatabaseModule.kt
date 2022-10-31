package com.afrinaldi.core.di

import androidx.room.Room
import com.afrinaldi.core.data.source.local.room.NewsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<NewsDatabase>().newsDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            NewsDatabase::class.java,
            "News.db"
        ).fallbackToDestructiveMigration().build()
    }
}
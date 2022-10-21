package com.afrinaldi.beber.core.di

import androidx.room.Room
import com.afrinaldi.beber.core.data.source.local.room.NewsDatabase
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
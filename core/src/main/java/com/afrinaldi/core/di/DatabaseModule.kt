package com.afrinaldi.core.di

import androidx.room.Room
import com.afrinaldi.core.data.source.local.room.NewsDatabase
import net.sqlcipher.database.SQLiteDatabase.getBytes
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<NewsDatabase>().newsDao() }
    single {
        val passphrase: ByteArray = getBytes("nal".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            NewsDatabase::class.java,
            "News.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}
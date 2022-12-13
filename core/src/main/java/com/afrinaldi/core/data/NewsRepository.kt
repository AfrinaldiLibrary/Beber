package com.afrinaldi.core.data

import android.util.Log
import com.afrinaldi.core.data.source.local.LocalDataSource
import com.afrinaldi.core.data.source.remote.RemoteDataSource
import com.afrinaldi.core.data.source.remote.network.ApiResponse
import com.afrinaldi.core.data.source.remote.response.ArticlesItem
import com.afrinaldi.core.domain.model.News
import com.afrinaldi.core.domain.repository.INewsRepository
import com.afrinaldi.core.utils.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): INewsRepository {
    override fun getAllNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<ArticlesItem>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getBreakingNews().map {
                    Log.e("toDomain", it.toString())
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data == null || data.isEmpty()
            
            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getBreakingNews()

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val newsList = DataMapper.mapResponsesToEntities(data, BREAKING)
                localDataSource.insertNews(newsList)
                Log.e("toEntities", newsList.toString())
            }
        }.asFlow()

    override fun getBookmarkNews(): Flow<List<News>> {
        return localDataSource.getBookmarkNews().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setBookmarkNews(news: News, isBookmark: Boolean) {
        val newsEntity = DataMapper.mapDomainToEntity(news)
        appExecutors.diskIO().execute {
            localDataSource.setBookmarkNews(newsEntity, isBookmark)
        }
    }

    override fun getSportNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<ArticlesItem>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getSportNews().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getSportNews()

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val newsList = DataMapper.mapResponsesToEntities(data, SPORTS)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    override fun getTechNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<ArticlesItem>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getTechNews().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getTechNews()

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val newsList = DataMapper.mapResponsesToEntities(data, TECH)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    override fun getBusinessNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<ArticlesItem>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getBusinessNews().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data == null || data.isEmpty()


            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getBusinessNews()

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val newsList = DataMapper.mapResponsesToEntities(data, BUSINESS)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    override fun getHealthNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<ArticlesItem>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getHealthNews().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getHealthNews()

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val newsList = DataMapper.mapResponsesToEntities(data, HEALTH)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    override suspend fun deleteNews() {
        localDataSource.deleteNews()
    }
}
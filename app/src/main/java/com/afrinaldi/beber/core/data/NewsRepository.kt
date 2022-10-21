package com.afrinaldi.beber.core.data

import com.afrinaldi.beber.core.data.source.local.LocalDataSource
import com.afrinaldi.beber.core.data.source.remote.RemoteDataSource
import com.afrinaldi.beber.core.data.source.remote.network.ApiResponse
import com.afrinaldi.beber.core.data.source.remote.response.ArticlesItem
import com.afrinaldi.beber.core.domain.model.News
import com.afrinaldi.beber.core.domain.repository.INewsRepository
import com.afrinaldi.beber.core.utils.AppExecutors
import com.afrinaldi.beber.core.utils.DataMapper
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
                return localDataSource.getAllNews().map {
                    DataMapper.mapEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
//                data == null || data.isEmpty()
                true
            
            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem?>>> =
                remoteDataSource.getAllNews()

            override suspend fun saveCallResult(data: List<ArticlesItem?>) {
                val newsList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    override fun getFavoriteNews(): Flow<List<News>> {
        return localDataSource.getFavoriteNews().map {
            DataMapper.mapEntityToDomain(it)
        }
    }

    override fun setFavoriteNews(news: News, isFavorite: Boolean) {
        val newsEntity = DataMapper.mapDomainToEntity(news)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteNews(newsEntity, isFavorite)
        }
    }
}
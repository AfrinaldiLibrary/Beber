package com.afrinaldi.beber.core.data.source.remote

import android.util.Log
import com.afrinaldi.beber.core.data.source.remote.network.ApiResponse
import com.afrinaldi.beber.core.data.source.remote.network.ApiService
import com.afrinaldi.beber.core.data.source.remote.response.ArticlesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val apiService: ApiService) {
    suspend fun getAllNews(): Flow<ApiResponse<List<ArticlesItem>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getHeadlines()
                val dataArray = response.articles
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.articles))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

//        return when (category) {
//            "sportNews" -> {
//                flow {
//                    try {
//                        val response = apiService.getSports()
//                        val dataArray = response.articles
//                        if (dataArray.isNotEmpty()){
//                            emit(ApiResponse.Success(response.articles))
//                        } else {
//                            emit(ApiResponse.Empty)
//                        }
//                    } catch (e : Exception){
//                        emit(ApiResponse.Error(e.toString()))
//                    }
//                }.flowOn(Dispatchers.IO)
//            }
//            "techNews" -> {
//                flow {
//                    try {
//                        val response = apiService.getTechnology()
//                        val dataArray = response.articles
//                        if (dataArray.isNotEmpty()){
//                            emit(ApiResponse.Success(response.articles))
//                        } else {
//                            emit(ApiResponse.Empty)
//                        }
//                    } catch (e : Exception){
//                        emit(ApiResponse.Error(e.toString()))
//                    }
//                }.flowOn(Dispatchers.IO)
//            }
//            "businessNews" -> {
//                flow {
//                    try {
//                        val response = apiService.getBusiness()
//                        val dataArray = response.articles
//                        if (dataArray.isNotEmpty()){
//                            emit(ApiResponse.Success(response.articles))
//                        } else {
//                            emit(ApiResponse.Empty)
//                        }
//                    } catch (e : Exception){
//                        emit(ApiResponse.Error(e.toString()))
//                    }
//                }.flowOn(Dispatchers.IO)
//            }
//            "healthNews" -> {
//                flow {
//                    try {
//                        val response = apiService.getHealth()
//                        val dataArray = response.articles
//                        if (dataArray.isNotEmpty()){
//                            emit(ApiResponse.Success(response.articles))
//                        } else {
//                            emit(ApiResponse.Empty)
//                        }
//                    } catch (e : Exception){
//                        emit(ApiResponse.Error(e.toString()))
//                    }
//                }.flowOn(Dispatchers.IO)
//            }
//            else -> {
//                flow {
//                    try {
//                        val response = apiService.getHeadlines()
//                        val dataArray = response.articles
//                        if (dataArray.isNotEmpty()){
//                            emit(ApiResponse.Success(response.articles))
//                        } else {
//                            emit(ApiResponse.Empty)
//                        }
//                    } catch (e : Exception){
//                        emit(ApiResponse.Error(e.toString()))
//                    }
//                }.flowOn(Dispatchers.IO)
//            }
//        }
    }

    suspend fun getSportNews(): Flow<ApiResponse<List<ArticlesItem>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getSports()
                val dataArray = response.articles
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.articles))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTechNews(): Flow<ApiResponse<List<ArticlesItem>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getTechnology()
                val dataArray = response.articles
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.articles))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getBusinessNews(): Flow<ApiResponse<List<ArticlesItem>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getBusiness()
                val dataArray = response.articles
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.articles))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getHealthNews(): Flow<ApiResponse<List<ArticlesItem>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getHealth()
                val dataArray = response.articles
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.articles))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}
package com.afrinaldi.core.data.source.remote.network

import com.afrinaldi.core.BuildConfig.API_KEY
import com.afrinaldi.core.data.source.remote.response.NewsResponse
import com.afrinaldi.core.utils.BUSINESS
import com.afrinaldi.core.utils.HEALTH
import com.afrinaldi.core.utils.SPORTS
import com.afrinaldi.core.utils.TECH
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getBreaking(
        @Query("country") country: String = "id",
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("top-headlines")
    suspend fun getSports(
        @Query("country") country: String = "id",
        @Query("category") category: String = SPORTS,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("top-headlines")
    suspend fun getTechnology(
        @Query("country") country: String = "id",
        @Query("category") category: String = TECH,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("top-headlines")
    suspend fun getBusiness(
        @Query("country") country: String = "id",
        @Query("category") category: String = BUSINESS,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("top-headlines")
    suspend fun getHealth(
        @Query("country") country: String = "id",
        @Query("category") category: String = HEALTH,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}
package com.afrinaldi.beber.core.data.source.remote.network

import com.afrinaldi.beber.BuildConfig.API_KEY
import com.afrinaldi.beber.core.data.source.remote.response.NewsResponse
import com.afrinaldi.beber.core.utils.BUSINESS
import com.afrinaldi.beber.core.utils.HEALTH
import com.afrinaldi.beber.core.utils.SPORTS
import com.afrinaldi.beber.core.utils.TECH
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

    @GET("everything")
    suspend fun getNewsSearch(
        @Query("q") keyword: String?,
        @Query("language") language: String?,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}
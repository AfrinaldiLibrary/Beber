package com.afrinaldi.beber.core.data.source.remote.network

import com.afrinaldi.beber.BuildConfig.API_KEY
import com.afrinaldi.beber.core.data.source.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String = "id",
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("top-headlines")
    suspend fun getSports(
        @Query("country") country: String = "id",
        @Query("category") category: String?,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("top-headlines")
    suspend fun getTechnology(
        @Query("country") country: String = "id",
        @Query("category") category: String?,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("top-headlines")
    suspend fun getBusiness(
        @Query("country") country: String = "id",
        @Query("category") category: String?,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("top-headlines")
    suspend fun getHealth(
        @Query("country") country: String = "id",
        @Query("category") category: String?,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("top-headlines")
    suspend fun getEntertainment(
        @Query("country") country: String = "id",
        @Query("category") category: String?,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun getNewsSearch(
        @Query("q") keyword: String?,
        @Query("language") language: String?,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}
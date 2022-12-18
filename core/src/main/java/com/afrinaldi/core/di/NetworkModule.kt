package com.afrinaldi.core.di

import androidx.viewbinding.BuildConfig
import com.afrinaldi.core.BuildConfig.BASE_URL
import com.afrinaldi.core.data.source.remote.network.ApiService
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val loggingInterceptor = if (BuildConfig.DEBUG){
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        val hostname = "newsapi.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/svXYI8MQpjkQ2SAbnIiqZOvk/sdbTlWScBbeJk4Legk=")
            .add(hostname, "sha256/7xmA6N1F1gp6ikj57Bg4DMG0jfUB+mZsEL4mZO0qbfU=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}
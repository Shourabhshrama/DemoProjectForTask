package com.app.demoprojectfortask.di

import com.app.demoprojectfortask.BuildConfig
import com.app.demoprojectfortask.data.remote.api.ApiService
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

const val BASEURL = "https://jsonplaceholder.typicode.com/"

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {


    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Provides
    @Singleton
    fun retrofit(
        okHttpClient: OkHttpClient
    ): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASEURL).build().create()
    }


}
package com.example.myapplication.di

import com.example.myapplication.Constants.BASE_URL
import com.example.myapplication.data.remote.EmployeeAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
        builder.addInterceptor(httpLoggingInterceptor)
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideEmployeeAPI(okHttpClient: OkHttpClient): EmployeeAPI {
        return Retrofit.Builder().client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(EmployeeAPI::class.java)
    }
}
package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.Constants.DATABASE_NAME
import com.example.myapplication.data.local.EmployeeDao
import com.example.myapplication.data.local.EmployeeDatabase
import com.example.myapplication.data.DefaultEmployeeRepository
import com.example.myapplication.data.EmployeeDataSource
import com.example.myapplication.data.EmployeeRepository
import com.example.myapplication.data.local.EmployeeLocalDataSource
import com.example.myapplication.data.remote.EmployeeAPI
import com.example.myapplication.data.remote.EmployeeRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteEmployeeDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalEmployeeDataSource

    @Singleton
    @RemoteEmployeeDataSource
    @Provides
    fun provideEmployeeRemoteDataSource(
        employeeAPI: EmployeeAPI
    ): EmployeeDataSource {
        return EmployeeRemoteDataSource(
            employeeAPI
        )
    }

    @Singleton
    @LocalEmployeeDataSource
    @Provides
    fun provideEmployeeLocalDataSource(
        employeeDao: EmployeeDao
    ): EmployeeDataSource {
        return EmployeeLocalDataSource(
            employeeDao
        )
    }

    @Singleton
    @Provides
    fun provideEmployeeDatabase(
        @ApplicationContext context: Context,
        callback: EmployeeDatabase.Callback
    ) = Room.databaseBuilder(context, EmployeeDatabase::class.java, DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Singleton
    @Provides
    fun provideEmployeeDao(
        database: EmployeeDatabase
    ) = database.employeeDao()

    @Singleton
    @Provides
    fun provideDefaultEmployeeRepository(
        @LocalEmployeeDataSource employeeLocalDataSource: EmployeeDataSource,
        @RemoteEmployeeDataSource employeeRemoteDataSource: EmployeeDataSource
    ) = DefaultEmployeeRepository(
        employeeLocalDataSource,
        employeeRemoteDataSource
    ) as EmployeeRepository
}
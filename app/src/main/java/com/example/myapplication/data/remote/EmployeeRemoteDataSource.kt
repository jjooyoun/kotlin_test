package com.example.myapplication.data.remote

import com.example.myapplication.data.remote.response.EmployeeResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import javax.inject.Inject

class EmployeeRemoteDataSource @Inject constructor(
    private val employeeAPI: EmployeeAPI,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getEmployees(): Response<EmployeeResponse> {
        return employeeAPI.getEmployees()
    }
}
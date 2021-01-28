package com.example.myapplication.data.remote

import com.example.myapplication.data.remote.response.EmployeeResponse
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeAPI {

    @GET("sq-mobile-interview/employees.json")
    suspend fun getEmployees(): Response<EmployeeResponse>

    @GET("sq-mobile-interview/employees_malformed.json")
    suspend fun getError(): Response<EmployeeResponse>

    @GET("sq-mobile-interview/employees_empty.json")
    suspend fun getEmpty(): Response<EmployeeResponse>
}
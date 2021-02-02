package com.example.myapplication.data.remote

import com.example.myapplication.data.EmployeeDataSource
import com.example.myapplication.data.local.Employee
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class EmployeeRemoteDataSource internal constructor(
    private val employeeAPI: EmployeeAPI,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : EmployeeDataSource {
    override suspend fun insertEmployee(employee: Employee) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEmployee(employee: Employee) {
        TODO("Not yet implemented")
    }

    override fun observeAllEmployee(): Flow<List<Employee>> {
        TODO("Not yet implemented")
    }
}
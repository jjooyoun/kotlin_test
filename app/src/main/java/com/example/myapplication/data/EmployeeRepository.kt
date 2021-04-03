package com.example.myapplication.data

import com.example.myapplication.data.local.Employee
import com.example.myapplication.util.Resource
import kotlinx.coroutines.flow.Flow

interface EmployeeRepository {

    suspend fun insertEmployee(employee: Employee)

    suspend fun deleteEmployee(employee: Employee)

    fun observeAllEmployee(): Flow<Resource<List<Employee>>>
}

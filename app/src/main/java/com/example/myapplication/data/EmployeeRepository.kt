package com.example.myapplication.data

import com.example.myapplication.data.local.Employee
import kotlinx.coroutines.flow.Flow

interface EmployeeRepository {

    suspend fun insertEmployee(employee: Employee)

    suspend fun deleteEmployee(employee: Employee)

    fun observeAllEmployee(): Flow<List<Employee>>
}

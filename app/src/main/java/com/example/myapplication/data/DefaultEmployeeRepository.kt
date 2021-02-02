package com.example.myapplication.data

import com.example.myapplication.data.local.Employee
import kotlinx.coroutines.flow.Flow

class DefaultEmployeeRepository(
    private val employeeLocalDataSource: EmployeeDataSource,
    private val employeeRemoteDataSource: EmployeeDataSource,
) : EmployeeRepository {
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

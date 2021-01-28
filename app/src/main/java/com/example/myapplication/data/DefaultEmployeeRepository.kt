package com.example.myapplication.data

import androidx.lifecycle.LiveData
import com.example.myapplication.data.local.Employee

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

    override fun observeAllEmployee(): LiveData<List<Employee>> {
        TODO("Not yet implemented")
    }
}

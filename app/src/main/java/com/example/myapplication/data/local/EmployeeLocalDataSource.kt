package com.example.myapplication.data.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class EmployeeLocalDataSource @Inject constructor(
    private val employeeDao: EmployeeDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun insertEmployee(employee: Employee) {
        TODO("Not yet implemented")
    }

    suspend fun insertAllEmployee(employeeList: List<Employee>) {
        employeeDao.insertAllEmployee(employeeList)
    }

    suspend fun deleteEmployee(employee: Employee) {
        employeeDao.deleteEmployee(employee)
    }

    fun observeAllEmployee(): List<Employee> {
        return employeeDao.observeAllEmployee()
    }
}
package com.example.myapplication.data.local

import androidx.lifecycle.LiveData
import com.example.myapplication.data.EmployeeDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class EmployeeLocalDataSource internal constructor(
    private val employeeDao: EmployeeDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : EmployeeDataSource {
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
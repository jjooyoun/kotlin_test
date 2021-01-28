package com.example.myapplication.data

import androidx.lifecycle.LiveData
import com.example.myapplication.data.local.Employee

interface EmployeeRepository {

    suspend fun insertEmployee(employee: Employee)

    suspend fun deleteEmployee(employee: Employee)

    fun observeAllEmployee(): LiveData<List<Employee>>
}

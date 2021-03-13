package com.example.myapplication.data

import com.example.myapplication.data.local.Employee
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeEmployeeRepository : EmployeeRepository {

    private val employeeList = mutableListOf<Employee>()

    private val observableEmployeeList = MutableStateFlow<List<Employee>>(employeeList)

    private fun refreshFlowData() {
        observableEmployeeList.value = employeeList
    }

    override suspend fun insertEmployee(employee: Employee) {
        employeeList.add(employee)
        refreshFlowData()
    }

    override suspend fun deleteEmployee(employee: Employee) {
        employeeList.remove(employee)
        refreshFlowData()
    }

    override fun observeAllEmployee(): Flow<List<Employee>> {
        return observableEmployeeList
    }
}
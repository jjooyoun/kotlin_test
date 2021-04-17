package com.example.myapplication.data

import com.example.myapplication.data.local.Employee
import com.example.myapplication.data.local.EmployeeLocalDataSource
import com.example.myapplication.data.remote.EmployeeRemoteDataSource
import com.example.myapplication.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultEmployeeRepository @Inject constructor(
    private val employeeLocalDataSource: EmployeeLocalDataSource,
    private val employeeRemoteDataSource: EmployeeRemoteDataSource,
) : EmployeeRepository {
    override suspend fun insertEmployee(employee: Employee) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEmployee(employee: Employee) {
        employeeLocalDataSource.deleteEmployee(employee)
    }

    override fun observeAllEmployee(): Flow<Resource<List<Employee>>> {
        return flow {
            emit(Resource.loading(employeeLocalDataSource.observeAllEmployee()))
            val response = employeeRemoteDataSource.getEmployees()
            if (response.isSuccessful) {
                response.body()?.employeeList.let {
                    val employeeRoomList = it?.map { employee ->
                        Employee(
                            employee.uuid,
                            employee.full_name,
                            employee.phone_number,
                            employee.email_address,
                            employee.biography,
                            employee.photo_url_small,
                            employee.photo_url_large,
                            employee.team,
                            employee.employee_type,
                        )
                    }

                    if (employeeRoomList != null) {
                        employeeLocalDataSource.insertAllEmployee(employeeRoomList)
                    }
                }
                emit(Resource.success(employeeLocalDataSource.observeAllEmployee()))
            }
        }.flowOn(Dispatchers.IO)
    }
}

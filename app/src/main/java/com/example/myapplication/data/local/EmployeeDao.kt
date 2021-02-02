package com.example.myapplication.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    @Query("SELECT * FROM employees")
    fun observeAllEmployee(): Flow<List<Employee>>
}

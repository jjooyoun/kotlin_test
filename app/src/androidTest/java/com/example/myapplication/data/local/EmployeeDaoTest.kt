package com.example.myapplication.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.myapplication.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class EmployeeDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: EmployeeDatabase
    private lateinit var dao: EmployeeDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            EmployeeDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.employeeDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertEmployee() = runBlocking {
        val employee = Employee(
            id = 1, "", "", "",
            "", "", "",
            "", "", ""
        )
        dao.insertEmployee(employee)

        val allEmployeeList = dao.observeAllEmployee().asLiveData().getOrAwaitValue()

        assertThat(allEmployeeList).contains(employee)
    }

    @Test
    fun deleteEmployee() = runBlocking {
        val employee = Employee(
            id = 1, "", "", "",
            "", "", "",
            "", "", ""
        )
        dao.insertEmployee(employee)
        dao.deleteEmployee(employee)

        val allEmployeeList = dao.observeAllEmployee().asLiveData().getOrAwaitValue()
        assertThat(allEmployeeList).doesNotContain(employee)
    }
}
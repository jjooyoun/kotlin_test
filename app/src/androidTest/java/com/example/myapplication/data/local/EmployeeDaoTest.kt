package com.example.myapplication.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.test.filters.SmallTest
import com.example.myapplication.getOrAwaitValue
import com.example.myapplication.launchFragmentInHiltContainer
import com.example.myapplication.ui.employee.EmployeeFragment
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class EmployeeDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: EmployeeDatabase
    private lateinit var dao: EmployeeDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.employeeDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

//    @Test
//    fun testLaunchFragmentInHiltContainer() {
//        launchFragmentInHiltContainer<EmployeeFragment> {
//
//        }
//    }

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
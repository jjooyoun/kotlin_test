package com.example.myapplication.ui.employee

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmployeeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: EmployeeViewModel

    @Before
    fun setup() {
//        viewModel = EmployeeViewModel(FakeEmployeeRepository)
    }

    @Test
    fun `insert employee item with empty`() {
        val string = buildString {
            append("")
        }
    }
}
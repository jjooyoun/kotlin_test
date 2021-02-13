package com.example.myapplication.ui.detail

import androidx.lifecycle.*
import com.example.myapplication.data.EmployeeRepository
import com.example.myapplication.data.PreferencesManager
import com.example.myapplication.data.local.Employee
import dagger.assisted.AssistedInject

class DetailViewModel @AssistedInject constructor(
    private val employeeRepository: EmployeeRepository,
    private val preferencesManager: PreferencesManager,
    private val state: SavedStateHandle
) : ViewModel() {
    val employee = state.get<Employee>("employee")
    var fullName = state.get<String>("full_name") ?: employee?.full_name ?: ""
        set(value) {
            field = value
            state.set("full_name", value)
        }
}
package com.example.myapplication.ui.employee

import androidx.lifecycle.*
import com.example.myapplication.data.EmployeeRepository
import com.example.myapplication.data.PreferencesManager
import com.example.myapplication.data.local.Employee
import com.example.myapplication.util.Event
import com.example.myapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    val employee = employeeRepository.observeAllEmployee().asLiveData()

    fun deleteEmployee(employee: Employee) = viewModelScope.launch {
        employeeRepository.deleteEmployee(employee)
    }

    private val employeeEventChannel = Channel<EmployeeEvent> { }
    val employeeEvent = employeeEventChannel.receiveAsFlow()

    val preferencesFlow = preferencesManager.preferencesFlow

    fun updateTest(test: String) = viewModelScope.launch {
        preferencesManager.updateTest(test)
    }

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _test = MutableLiveData<Event<Resource<Boolean>>>()
    val test: LiveData<Event<Resource<Boolean>>> = _test

    fun onEmployeeSelected(employee: Employee) {}

    sealed class EmployeeEvent {
        data class TestMessage(val employee: Employee) : EmployeeEvent()
        object TestNavigateMessage : EmployeeEvent()
    }
}
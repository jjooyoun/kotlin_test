package com.example.myapplication.ui.employee

import androidx.lifecycle.*
import com.example.myapplication.data.EmployeeRepository
import com.example.myapplication.data.PreferencesManager
import com.example.myapplication.data.local.Employee
import dagger.assisted.AssistedInject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class EmployeeViewModel @AssistedInject constructor(
    private val employeeRepository: EmployeeRepository,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    val employee = employeeRepository.observeAllEmployee().asLiveData()

    val preferencesFlow = preferencesManager.preferencesFlow

    private val employeeEventChannel = Channel<EmployeeEvent> { }
    val employeeEvent = employeeEventChannel.receiveAsFlow()

    fun updateTest(test: String) = viewModelScope.launch {
        preferencesManager.updateTest(test)
    }

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun onEmployeeSelected(employee: Employee) {}

    sealed class EmployeeEvent {
        data class TestMessage(val employee: Employee) : EmployeeEvent()
    }
}
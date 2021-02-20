package com.example.myapplication.ui

import androidx.lifecycle.*
import com.example.myapplication.data.EmployeeRepository
import com.example.myapplication.data.PreferencesManager
import dagger.assisted.AssistedInject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel @AssistedInject constructor(
    private val employeeRepository: EmployeeRepository,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val mainEventChannel = Channel<MainEvent> { }
    val mainEvent = mainEventChannel.receiveAsFlow()

    fun onGetEmployeeClick() = viewModelScope.launch {
        mainEventChannel.send(MainEvent.NavigateToEmployeeScreen)
    }

    sealed class MainEvent {
        object NavigateToEmployeeScreen : MainEvent()
    }
}
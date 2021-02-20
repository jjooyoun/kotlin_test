package com.example.myapplication.ui.dialog

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.EmployeeRepository
import com.example.myapplication.di.AppModule
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CustomDialogViewModel @AssistedInject constructor(
    private val employeeRepository: EmployeeRepository,
    @AppModule.ApplicationScope private val applicationScope: CoroutineScope
) : ViewModel() {

    fun test() = applicationScope.launch {
    }
}

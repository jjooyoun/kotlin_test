package com.example.myapplication.ui.employee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.myapplication.data.EmployeeRepository
import dagger.assisted.AssistedInject

class EmployeeViewModel @AssistedInject constructor(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    val employee = employeeRepository.observeAllEmployee().asLiveData()
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading
}
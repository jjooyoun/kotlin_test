package com.example.myapplication.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EmployeeResponse(

    @SerializedName("employees")
    @Expose
    val employeeList: List<Employee>? = null,
)

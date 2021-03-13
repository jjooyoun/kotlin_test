package com.example.myapplication.util

object EmployeeUtils {

    fun validateEmployeeName(
        fullName: String
    ): Boolean {
        if (fullName.isEmpty()) {
            return false
        }
        return true
    }
}
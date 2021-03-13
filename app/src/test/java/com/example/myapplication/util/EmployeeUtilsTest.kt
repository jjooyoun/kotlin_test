package com.example.myapplication.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class EmployeeUtilsTest {

    @Test
    fun `empty full name return false`() {
        val result = EmployeeUtils.validateEmployeeName(
            fullName = ""
        )
        assertThat(result).isFalse()
    }
}
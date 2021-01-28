package com.example.myapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees")
data class Employee(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var uuid: String,
    var full_name: String,
    var phone_number: String,
    var email_address: String,
    var biography: String,
    var photo_url_small: String,
    var photo_url_large: String,
    var team: String,
    var employee_type: String
)

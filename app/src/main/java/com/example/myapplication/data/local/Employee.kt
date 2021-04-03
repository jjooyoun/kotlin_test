package com.example.myapplication.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "employees")
@Parcelize
data class Employee(
    var uuid: String?,
    var full_name: String?,
    var phone_number: String?,
    var email_address: String?,
    var biography: String?,
    var photo_url_small: String?,
    var photo_url_large: String?,
    var team: String?,
    var employee_type: String?
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}

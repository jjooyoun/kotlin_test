package com.example.myapplication.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "employees",
    indices = arrayOf(Index(value = ["uuid"], unique = true))
)
@Parcelize
data class Employee(
    @ColumnInfo(name = "uuid") var uuid: String?,
    @ColumnInfo(name = "full_name") var full_name: String?,
    @ColumnInfo(name = "phone_number") var phone_number: String?,
    @ColumnInfo(name = "email_address") var email_address: String?,
    @ColumnInfo(name = "biography") var biography: String?,
    @ColumnInfo(name = "photo_url_small") var photo_url_small: String?,
    @ColumnInfo(name = "photo_url_large") var photo_url_large: String?,
    @ColumnInfo(name = "team") var team: String?,
    @ColumnInfo(name = "employee_type") var employee_type: String?
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}

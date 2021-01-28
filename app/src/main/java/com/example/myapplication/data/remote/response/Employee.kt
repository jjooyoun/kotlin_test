package com.example.myapplication.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Employee(

    @SerializedName("uuid")
    @Expose
    var uuid: String? = null,

    @SerializedName("full_name")
    @Expose
    var full_name: String? = null,

    @SerializedName("phone_number")
    @Expose
    var phone_number: String? = null,

    @SerializedName("email_address")
    @Expose
    var email_address: String? = null,

    @SerializedName("biography")
    @Expose
    var biography: String? = null,

    @SerializedName("photo_url_small")
    @Expose
    var photo_url_small: String? = null,

    @SerializedName("photo_url_large")
    @Expose
    var photo_url_large: String? = null,

    @SerializedName("team")
    @Expose
    var team: String? = null,

    @SerializedName("employee_type")
    @Expose
    var employee_type: String? = null
)

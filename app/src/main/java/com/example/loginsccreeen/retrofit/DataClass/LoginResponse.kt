package com.example.loginsccreeen.retrofit.DataClass

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("response") val details: Employee
) : Serializable

data class Employee(
    @SerializedName("id") val id: String,
    @SerializedName("employee_type") val employee_type: String,
    @SerializedName("user_name") val user_name: String,
    @SerializedName("emp_id") val emp_id: String,
    @SerializedName("title") val title: String,
    @SerializedName("firstname") val firstname: String,
    @SerializedName("lastname") val lastname: String,
    @SerializedName("email") val email: String,
    @SerializedName("mobile") val mobile: String,
    @SerializedName("alt_mobile") val alt_mobile: String?,
    @SerializedName("work_area") val work_area: String?,
    @SerializedName("address1") val address1: String,
    @SerializedName("address2") val address2: String,
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String,
    @SerializedName("district") val district: String,
    @SerializedName("location") val location: String,
    @SerializedName("cng_filling_station") val cng_filling_station: String,
    @SerializedName("pincode") val pincode: String,
    @SerializedName("landmark") val landmark: String,
    @SerializedName("status") val status: String,
    @SerializedName("created_by") val created_by: String?,
    @SerializedName("created_at") val created_at: String?,
    @SerializedName("updated_by") val updated_by: String?,
    @SerializedName("updated_at") val updated_at: String?,
    @SerializedName("last_login_date") val last_login_date: String,
    @SerializedName("token") val token: String,
    @SerializedName("mother_station") val mother_station: String,
    @SerializedName("mother_station_name") val mother_station_name: String,
    @SerializedName("district_name") val district_name: String,
    @SerializedName("state_name") val state_name: String,
    @SerializedName("filling_station_name") val filling_station_name: String,
    @SerializedName("location_name") val location_str: String?,
    @SerializedName("role") val role: String?

) : Serializable


data class AppverersionResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("error") val error: String,
    @SerializedName("response") val appdetails: AppDetails
) : Serializable

data class AppDetails(
    @SerializedName("android_version") val androidversion: String,
    @SerializedName("ios_version") val iosresponse: String,
    @SerializedName("android_forcefully_update") val androidUpdate: Boolean,
) : Serializable
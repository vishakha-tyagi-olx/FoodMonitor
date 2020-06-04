package com.shield.foodmonitor.data.model

import com.google.gson.annotations.SerializedName

data class ApiGeneralResponse(@SerializedName("status") val apiStatus: String?,
                              @SerializedName("msg") val apiMsg: String?) {
}
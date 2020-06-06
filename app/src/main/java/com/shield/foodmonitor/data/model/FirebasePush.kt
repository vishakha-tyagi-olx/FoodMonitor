package com.shield.foodmonitor.data.model

import com.google.gson.annotations.SerializedName

data class FirebasePush(@SerializedName("to") val token: String?, @SerializedName("collapse_key") val collapseKey: String?,
                        @SerializedName("data") val dataPayload: DataPayload) {
}
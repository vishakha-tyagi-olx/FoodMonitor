package com.shield.foodmonitor.data.model

import com.google.gson.annotations.SerializedName

data class PushResponseResult(@SerializedName("message_id") val messageId: String) {
}
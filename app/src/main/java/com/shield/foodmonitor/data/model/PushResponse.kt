package com.shield.foodmonitor.data.model

import com.google.gson.annotations.SerializedName

class PushResponse(@SerializedName("multicast_id") val token: Long, @SerializedName("success") val success: Int,
                   @SerializedName("failure") val failure: Int,  @SerializedName("canonical_ids") val can_id: Int,
                   @SerializedName("results") val results: List<PushResponseResult>) {
}
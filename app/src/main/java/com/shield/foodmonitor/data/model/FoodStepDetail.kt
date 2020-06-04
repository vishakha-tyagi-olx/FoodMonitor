package com.shield.foodmonitor.data.model

import com.google.gson.annotations.SerializedName

data class FoodStepDetail(@SerializedName("status") val orderStatus: String?,
                          @SerializedName("imageUrl") val picture: String?) {
}
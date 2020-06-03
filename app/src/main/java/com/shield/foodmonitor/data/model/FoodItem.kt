package com.shield.foodmonitor.data.model

import com.google.gson.annotations.SerializedName

data class FoodItem(@SerializedName("name")val name: String?, @SerializedName("img")val image: String?, @SerializedName("price")val price: String?, @SerializedName("name")val isVeg: Boolean?) {
}
package com.shield.foodmonitor.data.model

import com.google.gson.annotations.SerializedName

data class FoodItem(@SerializedName("name")val name: String?, @SerializedName("imageUrl")val image: String?, @SerializedName("price")val price: String?, @SerializedName("isVeg")val isVeg: Boolean?) {
}
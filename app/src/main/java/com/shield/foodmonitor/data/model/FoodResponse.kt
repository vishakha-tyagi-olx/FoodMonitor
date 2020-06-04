package com.shield.foodmonitor.data.model

import com.google.gson.annotations.SerializedName

data class FoodResponse(@SerializedName("items")val foodList: List<FoodItem>) {
}
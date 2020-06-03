package com.shield.foodmonitor.data.repository

import com.shield.foodmonitor.data.api.ApiHelper
import com.shield.foodmonitor.data.model.FoodItem
import io.reactivex.Single

class FoodRepository(private val apiHelper: ApiHelper) {

    fun getFoodList(): Single<List<FoodItem>> {
        return apiHelper.getFoodList()
    }
}
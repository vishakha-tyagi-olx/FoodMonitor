package com.shield.foodmonitor.data.api

import com.shield.foodmonitor.data.model.FoodItem
import io.reactivex.Single

interface ApiService {

    fun getFoodList(): Single<List<FoodItem>>
}
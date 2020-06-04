package com.shield.foodmonitor.data.api

import com.shield.foodmonitor.data.model.ApiGeneralResponse
import com.shield.foodmonitor.data.model.FoodItem
import com.shield.foodmonitor.data.model.FoodResponse
import com.shield.foodmonitor.data.model.FoodStepDetail
import io.reactivex.Single

interface ApiService {

    fun getFoodList(): Single<FoodResponse>

    fun uploadFoodStepDetail(foodStepDetail: FoodStepDetail): Single<ApiGeneralResponse>
}
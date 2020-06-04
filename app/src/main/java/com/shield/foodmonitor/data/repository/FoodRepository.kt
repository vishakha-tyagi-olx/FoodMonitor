package com.shield.foodmonitor.data.repository

import com.shield.foodmonitor.data.api.ApiHelper
import com.shield.foodmonitor.data.api.ApiService
import com.shield.foodmonitor.data.model.ApiGeneralResponse
import com.shield.foodmonitor.data.model.FoodItem
import com.shield.foodmonitor.data.model.FoodResponse
import com.shield.foodmonitor.data.model.FoodStepDetail
import io.reactivex.Single

class FoodRepository(private val apiService: ApiService) {

    fun getFoodList(): Single<FoodResponse> {
        return apiService.getFoodList()
    }

    fun uploadFoodStepDetail(foodStepDetail: FoodStepDetail): Single<ApiGeneralResponse> {
        return apiService.uploadFoodStepDetail(foodStepDetail)
    }
}
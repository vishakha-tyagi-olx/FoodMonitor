package com.shield.foodmonitor.data.api

import com.shield.foodmonitor.data.model.ApiGeneralResponse
import com.shield.foodmonitor.data.model.FoodResponse
import com.shield.foodmonitor.data.model.FoodStepDetail
import io.reactivex.Single

class ApiHelper(private val apiService: ApiService) {

    fun getFoodList(): Single<FoodResponse> {
        return apiService.getFoodList()
    }

    fun uploadFoodStepDetail(foodStepDetail: FoodStepDetail): Single<ApiGeneralResponse> {
        return apiService.uploadFoodStepDetail(foodStepDetail)
    }

}
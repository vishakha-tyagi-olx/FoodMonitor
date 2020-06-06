package com.shield.foodmonitor.data.repository

import com.shield.foodmonitor.data.api.ApiHelper
import com.shield.foodmonitor.data.api.ApiService
import com.shield.foodmonitor.data.model.*
import io.reactivex.Single

class FoodRepository(private val apiService: ApiService) {

    fun getFoodList(): Single<FoodResponse> {
        return apiService.getFoodList()
    }

    fun getSafetyCheckList(): Single<TrackOrderResponse> {
        return apiService.getSafetyCheckList()
    }

    fun uploadFoodStepDetail(foodStepDetail: FoodStepDetail): Single<ApiGeneralResponse> {
        return apiService.uploadFoodStepDetail(foodStepDetail)
    }

    fun sendPush(){
        return apiService.sendPush()
    }

    fun deleteDb(){
        apiService.deleteDb()
    }
}
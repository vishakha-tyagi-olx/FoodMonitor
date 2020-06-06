package com.shield.foodmonitor.data.api

import com.shield.foodmonitor.data.model.*
import io.reactivex.Single

interface ApiService {

    fun getSafetyCheckList(): Single<TrackOrderResponse>

    fun getFoodList(): Single<FoodResponse>

    fun uploadFoodStepDetail(foodStepDetail: FoodStepDetail): Single<ApiGeneralResponse>

    fun sendPush()

    fun deleteDb()
}
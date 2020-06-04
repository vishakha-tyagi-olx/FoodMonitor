package com.shield.foodmonitor.data.api

import com.shield.foodmonitor.data.model.ApiGeneralResponse
import com.shield.foodmonitor.data.model.FoodItem
import com.shield.foodmonitor.data.model.FoodResponse
import com.shield.foodmonitor.data.model.FoodStepDetail
import com.shield.foodmonitor.data.retrofit.RetrofitClientImpl
import io.reactivex.Single

class ApiServiceImpl: ApiService {

    override fun getFoodList(): Single<FoodResponse> {

        return RetrofitClientImpl.getApiClient().getFoodList()
    }

    override fun uploadFoodStepDetail(foodStepDetail: FoodStepDetail): Single<ApiGeneralResponse> {
        return RetrofitClientImpl.getApiClient().uploadFoodStepDetail(foodStepDetail.orderStatus!!, foodStepDetail.picture!!)
    }


}
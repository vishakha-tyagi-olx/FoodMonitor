package com.shield.foodmonitor.data.retrofit

import com.shield.foodmonitor.data.model.FoodResponse
import com.shield.foodmonitor.data.model.FoodStepDetail
import com.shield.foodmonitor.data.model.ApiGeneralResponse
import io.reactivex.Single
import retrofit2.http.*

interface RequestInterface {

    @GET("/getfooditems/items")
    fun getFoodList() : Single<FoodResponse>

    @POST("/tracking/create")
    fun uploadFoodStepDetail(@Query("status") status: String, @Body stepDetail: String): Single<ApiGeneralResponse>
}
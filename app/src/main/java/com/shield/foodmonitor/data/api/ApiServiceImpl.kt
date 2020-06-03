package com.shield.foodmonitor.data.api

import com.shield.foodmonitor.data.model.FoodItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

class ApiServiceImpl: ApiService {

    override fun getFoodList(): Single<List<FoodItem>> {
        return Single.error(Throwable())
    }
}
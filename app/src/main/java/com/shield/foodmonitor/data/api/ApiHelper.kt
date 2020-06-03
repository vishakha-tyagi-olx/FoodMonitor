package com.shield.foodmonitor.data.api

class ApiHelper(private val apiService: ApiService) {

    fun getFoodList() = apiService.getFoodList()

}
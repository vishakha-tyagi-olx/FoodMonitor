package com.shield.foodmonitor.data.retrofit

import com.shield.foodmonitor.data.api.ApiService
import com.shield.foodmonitor.data.api.ApiServiceImpl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientImpl {

    companion object {

        fun getApiClient(): RequestInterface {
            return Retrofit.Builder()
                .baseUrl("https://b9757287.eu-gb.apigw.appdomain.cloud")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .build().create(RequestInterface::class.java)
        }
    }
}
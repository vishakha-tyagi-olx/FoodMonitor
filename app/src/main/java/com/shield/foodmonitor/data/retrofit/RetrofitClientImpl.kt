package com.shield.foodmonitor.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClientImpl {

    companion object {

        private val httpClient = OkHttpClient.Builder()
        private var logging = HttpLoggingInterceptor()

        fun getApiClient(): Retrofit {
            logging.level = HttpLoggingInterceptor.Level.BODY
            return Retrofit.Builder()
                .baseUrl("https://b9757287.eu-gb.apigw.appdomain.cloud")
                .client(httpClient.addInterceptor(logging).build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }
}
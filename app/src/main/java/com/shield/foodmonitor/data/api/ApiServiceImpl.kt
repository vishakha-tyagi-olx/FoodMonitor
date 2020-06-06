package com.shield.foodmonitor.data.api

import android.util.Log
import com.shield.foodmonitor.FoodMonitorApplication
import com.shield.foodmonitor.data.model.*
import com.shield.foodmonitor.data.retrofit.RequestInterface
import com.shield.foodmonitor.data.retrofit.RetrofitClientImpl
import com.shield.foodmonitor.utils.Constants
import com.shield.foodmonitor.utils.Resource
import com.shield.foodmonitor.utils.Utility
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApiServiceImpl: ApiService {

    val wikiApiServe by lazy {
        RetrofitClientImpl.getApiClient().create(RequestInterface::class.java)
    }
    override fun getSafetyCheckList(): Single<TrackOrderResponse> {
        return wikiApiServe.getSafetyCheckList(Utility.getString(FoodMonitorApplication.applicationContext(), Constants.UNIQUE_ID)!!)
    }

    override fun getFoodList(): Single<FoodResponse> {

        return wikiApiServe.getFoodList()
    }

    override fun uploadFoodStepDetail(foodStepDetail: FoodStepDetail): Single<ApiGeneralResponse> {
        return wikiApiServe.uploadFoodStepDetail(Utility.getString(FoodMonitorApplication.applicationContext(), Constants.UNIQUE_ID)!!, Utility.getFirebaseToken(FoodMonitorApplication.applicationContext())!!, foodStepDetail.orderStatus!!, foodStepDetail.picture!!)
    }

    override fun sendPush() {
        wikiApiServe.sendPush(
            "https://fcm.googleapis.com/fcm/send",
            Utility.createPush()
            ).subscribeOn(
            Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ userList ->
                Log.d("TAG","push")

            }, { throwable ->
               Log.d("TAG","Something Went Wrong")
            })

    }

}
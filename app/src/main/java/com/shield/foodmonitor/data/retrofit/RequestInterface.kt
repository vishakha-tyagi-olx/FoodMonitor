package com.shield.foodmonitor.data.retrofit

import com.shield.foodmonitor.data.model.*
import io.reactivex.Single
import retrofit2.http.*
import java.util.*

interface RequestInterface {

    @GET("/getfooditems/items")
    fun getFoodList() : Single<FoodResponse>

    @GET("/trackingdetails/getDetails")
    fun getSafetyCheckList() : Single<TrackOrderResponse>

    @POST("/tracking/create")
    fun uploadFoodStepDetail(@Query("token") token: String, @Query("status") status: String, @Body stepDetail: String): Single<ApiGeneralResponse>

    @POST
    @Headers("Accept: application/json",
    "Authorization:key=AAAAYLYdujs:APA91bE8o7eX_1VWDKQXWcwLS_zsxsT8D398i9YpeK8lU2m3uRn2paYYt-9INnkKFzs-PiW75CGZvHZLyT1R5aOBL-wvYhpxPui42vDDwj9SpWqa-nYP2Mctt_4yPlacBsCWPBCGAn-U",
    "Content-Type:application/json")
    fun sendPush(@Url url: String, @Body push: FirebasePush) : Single<PushResponse>

    @DELETE("/deletetrackingdetails/tracking")
    fun deleteDb(): Single<DeleteApiResponse>
}
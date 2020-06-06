package com.shield.foodmonitor.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shield.foodmonitor.data.model.ApiGeneralResponse
import com.shield.foodmonitor.data.model.FoodResponse
import com.shield.foodmonitor.data.model.FoodStepDetail
import com.shield.foodmonitor.data.model.TrackOrderResponse
import com.shield.foodmonitor.data.repository.FoodRepository
import com.shield.foodmonitor.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TrackOrderViewModel (): BaseViewModel() {

    private val checkList = MutableLiveData<Resource<TrackOrderResponse>>()


    fun fetchOrderStatusList() {
        checkList.postValue(Resource.loading(null))
        compositeDisposable.add(
            foodRepository.getSafetyCheckList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userList ->
                    checkList.postValue(Resource.success(userList))
                }, { throwable ->
                    checkList.postValue(Resource.error("Something Went Wrong", null))
                })
        )
    }


    fun getOrderStatusList(): LiveData<Resource<TrackOrderResponse>> {
        return checkList
    }
}

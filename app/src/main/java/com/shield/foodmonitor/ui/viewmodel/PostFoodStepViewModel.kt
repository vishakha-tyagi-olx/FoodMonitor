package com.shield.foodmonitor.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shield.foodmonitor.data.model.ApiGeneralResponse
import com.shield.foodmonitor.data.model.FoodResponse
import com.shield.foodmonitor.data.model.FoodStepDetail
import com.shield.foodmonitor.data.repository.FoodRepository
import com.shield.foodmonitor.utils.Resource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PostFoodStepViewModel(): BaseViewModel() {

    private val apiResponseLiveData = MutableLiveData<Resource<ApiGeneralResponse>>()


    fun uploadStepStatus(foodStepDetail: FoodStepDetail){

        apiResponseLiveData.postValue(Resource.loading(null))
        compositeDisposable.add(
            foodRepository.uploadFoodStepDetail(foodStepDetail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ apiResponse ->
                    apiResponseLiveData.postValue(Resource.success(apiResponse))
                }, { throwable ->
                    apiResponseLiveData.postValue(Resource.error("Something Went Wrong, Upload again!", null))
                })
        )
    }

    fun getUploadStepStatus(): LiveData<Resource<ApiGeneralResponse>>{
        return apiResponseLiveData
    }

}
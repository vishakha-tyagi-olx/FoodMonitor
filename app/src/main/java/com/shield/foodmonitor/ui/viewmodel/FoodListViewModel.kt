package com.shield.foodmonitor.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shield.foodmonitor.data.api.ApiHelper
import com.shield.foodmonitor.data.model.FoodItem
import com.shield.foodmonitor.data.model.FoodResponse
import com.shield.foodmonitor.data.repository.FoodRepository
import com.shield.foodmonitor.utils.Resource
import com.shield.foodmonitor.utils.Utility
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FoodListViewModel(): BaseViewModel() {

    private val foodList = MutableLiveData<Resource<FoodResponse>>()


    init {
        fetchFoodList()
    }

    private fun fetchFoodList() {
        foodList.postValue(Resource.loading(null))
        compositeDisposable.add(
            foodRepository.getFoodList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userList ->
                    foodList.postValue(Resource.success(userList))
                }, { throwable ->
                    foodList.postValue(Resource.error("Something Went Wrong", null))
                })
        )

    }


    fun getFoodList(): LiveData<Resource<FoodResponse>> {
        return foodList
    }

}
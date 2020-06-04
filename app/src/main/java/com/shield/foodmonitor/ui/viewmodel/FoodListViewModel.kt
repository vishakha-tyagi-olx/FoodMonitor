package com.shield.foodmonitor.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shield.foodmonitor.data.api.ApiHelper
import com.shield.foodmonitor.data.model.FoodItem
import com.shield.foodmonitor.data.model.FoodResponse
import com.shield.foodmonitor.data.repository.FoodRepository
import com.shield.foodmonitor.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FoodListViewModel(val foodRepository: FoodRepository): ViewModel() {

    private val foodList = MutableLiveData<Resource<FoodResponse>>()

    private val compositeDisposable = CompositeDisposable()

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

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getFoodList(): LiveData<Resource<FoodResponse>> {
        return foodList
    }

}
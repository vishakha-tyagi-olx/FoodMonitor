package com.shield.foodmonitor.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.shield.foodmonitor.data.api.ApiServiceImpl
import com.shield.foodmonitor.data.repository.FoodRepository
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(): ViewModel() {

    val foodRepository = FoodRepository(ApiServiceImpl())

    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
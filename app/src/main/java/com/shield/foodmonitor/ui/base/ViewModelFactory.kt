package com.shield.foodmonitor.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shield.foodmonitor.data.api.ApiHelper
import com.shield.foodmonitor.data.repository.FoodRepository
import com.shield.foodmonitor.ui.viewmodel.FoodListViewModel

class ViewModelFactory (private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FoodListViewModel::class.java)) {
                return FoodListViewModel(FoodRepository(apiHelper)) as T
            }
            throw IllegalArgumentException("Unknown class name")
        }

    }
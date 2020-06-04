package com.shield.foodmonitor.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shield.foodmonitor.data.api.ApiHelper
import com.shield.foodmonitor.data.api.ApiService
import com.shield.foodmonitor.data.repository.FoodRepository
import com.shield.foodmonitor.ui.viewmodel.FoodListViewModel
import com.shield.foodmonitor.ui.viewmodel.PostFoodStepViewModel

class ViewModelFactory (private val apiService: ApiService) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FoodListViewModel::class.java)) {
                return FoodListViewModel(FoodRepository(apiService)) as T
            }
            else if(modelClass.isAssignableFrom(PostFoodStepViewModel::class.java)){
                return PostFoodStepViewModel(FoodRepository(apiService)) as T
            }
            throw IllegalArgumentException("Unknown class name")
        }

    }
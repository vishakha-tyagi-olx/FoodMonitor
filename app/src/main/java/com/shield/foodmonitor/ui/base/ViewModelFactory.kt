package com.shield.foodmonitor.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shield.foodmonitor.data.api.ApiHelper
import com.shield.foodmonitor.data.api.ApiService
import com.shield.foodmonitor.data.repository.FoodRepository
import com.shield.foodmonitor.ui.viewmodel.FoodListViewModel
import com.shield.foodmonitor.ui.viewmodel.PostFoodStepViewModel
import com.shield.foodmonitor.ui.viewmodel.TrackOrderViewModel

class ViewModelFactory () : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FoodListViewModel::class.java)) {
                return FoodListViewModel() as T
            }
            else if(modelClass.isAssignableFrom(PostFoodStepViewModel::class.java)){
                return PostFoodStepViewModel() as T
            }
            else if(modelClass.isAssignableFrom(TrackOrderViewModel::class.java)){
                return TrackOrderViewModel() as T
            }

            throw IllegalArgumentException("Unknown class name")
        }

    }
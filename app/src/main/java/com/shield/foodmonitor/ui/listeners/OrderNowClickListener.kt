package com.shield.foodmonitor.ui.listeners

import com.shield.foodmonitor.data.model.FoodItem

interface OrderNowClickListener {

    fun onOrderNowClick(foodItem: FoodItem)

    fun onshieldClickListener()
}
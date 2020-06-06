package com.shield.foodmonitor.data.model

import com.google.gson.annotations.SerializedName

class TrackOrderResponse (@SerializedName("body")val checkList: List<TrackItem>) {
}
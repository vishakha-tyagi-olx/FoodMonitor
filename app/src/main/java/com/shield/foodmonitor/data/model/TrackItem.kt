package com.shield.foodmonitor.data.model

import com.google.gson.annotations.SerializedName

data class TrackItem( @SerializedName("status")val status: String?, @SerializedName("image")val image: String? ){
}
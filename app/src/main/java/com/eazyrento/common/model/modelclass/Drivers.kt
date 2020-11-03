package com.eazyrento.common.model.modelclass


import com.google.gson.annotations.SerializedName

data class Drivers(
    @SerializedName("details")
    val details: Details
)
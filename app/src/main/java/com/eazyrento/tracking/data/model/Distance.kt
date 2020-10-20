package com.eazyrento.tracking.data.model


import com.google.gson.annotations.SerializedName

data class Distance(
    @SerializedName("value")
    val value: Int,
    @SerializedName("text")
    val text: String
)
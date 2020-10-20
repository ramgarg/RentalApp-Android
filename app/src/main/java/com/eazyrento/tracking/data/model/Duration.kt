package com.eazyrento.tracking.data.model


import com.google.gson.annotations.SerializedName

data class Duration(
    @SerializedName("value")
    val value: Int,
    @SerializedName("text")
    val text: String
)
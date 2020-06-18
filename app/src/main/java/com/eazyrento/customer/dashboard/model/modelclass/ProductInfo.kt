package com.eazyrento.customer.dashboard.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductInfo(
    val capacity: String,
    val fuel_type: String,
    val power: String
) : Parcelable
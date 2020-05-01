package com.eazyrento.common.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductDetail(
    val product_name: String,
    val quantity: Int,
    val start_date: String,
    val starting_price: Double
) : Parcelable
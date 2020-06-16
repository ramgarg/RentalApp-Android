package com.eazyrento.customer.dashboard.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MerchantProductDetails(
    val booking_price: Double,
    val merchant_quantity: Int,
    val product_name: String
) : Parcelable
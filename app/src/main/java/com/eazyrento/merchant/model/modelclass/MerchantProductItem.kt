package com.eazyrento.merchant.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MerchantProductItem(
    val id: Int,
    val price: Double,
    val product_id: Int,
    val product_image_url: String,
    val product_name: String
): Parcelable
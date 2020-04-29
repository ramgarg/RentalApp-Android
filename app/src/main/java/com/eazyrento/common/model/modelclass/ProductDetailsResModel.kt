package com.eazyrento.common.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductDetailsResModel(
    val base_price: Double,
    val id: Int,
    var is_wishlisted: Boolean,
    val name: String,
    val product_description: String,
//    val product_details: ProductDetailsInner,
    val product_image_url: String
) : Parcelable
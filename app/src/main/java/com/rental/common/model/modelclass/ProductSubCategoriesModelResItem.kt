package com.rental.common.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductSubCategoriesModelResItem(
    val description: String,
    val id: Int,
    val subcategory_image_url: String,
    val subcategory_name: String
) : Parcelable
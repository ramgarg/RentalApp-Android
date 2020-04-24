package com.eazyrento.common.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductCateItem(
    val category_image_url: String?,
    val category_name: String,
    val description: String,
    val display_name:String,
    val id: Int
) : Parcelable
{

    override fun toString(): String = display_name
}
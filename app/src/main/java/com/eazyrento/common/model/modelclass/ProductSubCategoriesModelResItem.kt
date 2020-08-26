package com.eazyrento.common.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductSubCategoriesModelResItem(
    val description: String,
    val id: Int,
    val subcategory_image_url: String,
    val subcategory_name: String,
    val display_name: String
) : Parcelable
{
    override fun toString(): String = display_name
}

@Parcelize
data class ProductListBySubCateModelResItem(
    val id:Int,
    val name:String,
    val product_image_url:String/*,
    val display_name: String*/
) : Parcelable
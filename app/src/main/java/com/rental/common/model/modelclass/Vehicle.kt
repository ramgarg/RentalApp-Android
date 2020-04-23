package com.rental.common.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vehicle(
    val category_image_url: String?,
    val category_name: String,
    val description: String,
    val id: Int
) : Parcelable
{

    override fun toString(): String = category_name
}
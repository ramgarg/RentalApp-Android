package com.rental.common.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductCategoriesResModel(
    val vehicles: List<Vehicle>
) : Parcelable
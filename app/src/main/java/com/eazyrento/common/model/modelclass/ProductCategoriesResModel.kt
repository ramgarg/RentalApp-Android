package com.eazyrento.common.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductCategoriesResModel(
    val productCateItems: List<ProductCateItem>
) : Parcelable
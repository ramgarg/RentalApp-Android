package com.eazyrento.common.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MasterResModelItem(
    val id: Int,
    val master_image_url: String?,
    val name: String,
    val display_name: String
) : Parcelable
{
    override fun toString(): String = display_name
}

package com.eazyrento.login.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddressInfo(
    var address_line: String?,
    var address_type: String?,
    var appartment: String?,
    var city: String?,
    var country: String?,
    var id: Int?=null,
    var is_default: Boolean,
    var latitude: Double,
    var longitude: Double,
    var state: String?
) : Parcelable

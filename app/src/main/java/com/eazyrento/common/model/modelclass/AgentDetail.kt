package com.eazyrento.common.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class AgentDetail(
    val full_name: String,
    val id: Int,
    val mobile_number: String,
    val profile_image: String
) : Parcelable
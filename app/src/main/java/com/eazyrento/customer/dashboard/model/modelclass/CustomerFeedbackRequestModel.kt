package com.eazyrento.customer.dashboard.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CustomerFeedbackRequestModel(
    var order_id: String?,

    var agent_id: Int?,
    var merchant_id: Int?,

    var review: String?,
    var rating: Float?

) :Parcelable {
    constructor():this("",-1,-1,"",0.0f)
}



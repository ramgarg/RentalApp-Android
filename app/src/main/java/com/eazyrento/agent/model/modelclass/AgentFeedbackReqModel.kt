package com.eazyrento.agent.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AgentFeedbackReqModel(
    var order_id: String?,
    var customer_id: Int?,
    var merchant_id: Int?,
    var review: String?,
    var rating: Float?
) : Parcelable {
    constructor():this("",-1,-1,"",0.0f)
}
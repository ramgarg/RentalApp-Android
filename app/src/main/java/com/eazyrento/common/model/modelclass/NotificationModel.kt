package com.eazyrento.common.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

abstract class BaseNoticationModel {
    abstract val deep_link: String?
    abstract val notification_id: String
}
@Parcelize
data class NotificatioOrderModel(
    override val deep_link: String?,
    override val notification_id: String,
    val order_id: String?
) : BaseNoticationModel(), Parcelable
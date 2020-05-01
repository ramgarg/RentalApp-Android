package com.eazyrento.common.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookingDashboardResModel(
    val bookings: List<Booking>,
    val completed_orders_count: Int,
    val in_progress_orders_count: Int,
    val rejected_orders_count: Int,
    val status: Int
) : Parcelable
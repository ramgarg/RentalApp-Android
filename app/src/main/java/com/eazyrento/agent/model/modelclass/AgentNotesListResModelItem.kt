package com.eazyrento.agent.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AgentNotesListResModelItem(
    val id: Int,
    val header: String,
    val description: String
) : Parcelable {
}
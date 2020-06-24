package com.eazyrento.agent.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AgentNotesListResModelItem(
    val id: Int,
    var header: String,
    var description: String
) : Parcelable {
}
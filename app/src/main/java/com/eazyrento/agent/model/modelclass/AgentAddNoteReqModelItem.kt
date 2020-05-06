package com.eazyrento.agent.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AgentAddNoteReqModelItem(
    var id:Int?,
    var header: String,
    var description: String

) : Parcelable
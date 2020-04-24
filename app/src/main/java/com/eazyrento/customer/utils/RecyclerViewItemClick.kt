package com.eazyrento.customer.utils

import com.eazyrento.common.model.modelclass.MasterResModelItem
import com.eazyrento.customer.dashboard.model.modelclass.Data
import com.eazyrento.customer.dashboard.model.modelclass.HomeResponse

interface RecyclerViewItemClick {
    fun onItemClick(item: Data)
}
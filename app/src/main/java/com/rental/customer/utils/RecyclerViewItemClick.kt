package com.rental.customer.utils

import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.dashboard.model.modelclass.HomeResponse

interface RecyclerViewItemClick {
    fun onItemClick(item: Data)
}
package com.eazyrento.common.view.activity

import android.os.Bundle
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderListResModel

class OrderFilter:BaseActivity() {

    lateinit var listOrderItems: CustomerOrderListResModel

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
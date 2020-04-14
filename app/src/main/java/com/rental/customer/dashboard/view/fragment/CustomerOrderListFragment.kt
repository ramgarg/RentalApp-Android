package com.rental.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rental.R
import com.rental.common.view.fragment.OrderListFragment

class CustomerOrderListFragment : OrderListFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val  view=inflater.inflate(R.layout.fragment_customer_order, container, false)

        viewVisibility(view)

        return  view
    }
}
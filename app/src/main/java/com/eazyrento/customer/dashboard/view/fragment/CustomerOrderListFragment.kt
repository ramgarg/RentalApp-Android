package com.eazyrento.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.view.adapter.OrderListBaseAdapter
import com.eazyrento.common.view.fragment.OrderListFragment
import com.eazyrento.customer.dashboard.model.modelclass.OrderDetailsResModel
import com.eazyrento.customer.dashboard.view.activity.CustomerOrderSummaryActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent

class CustomerOrderListFragment : OrderListFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val  view=inflater.inflate(R.layout.fragment_customer_order, container, false)

        viewVisibility(view)

        return  view
    }



    override fun <T, K> onViewClick(type: T, where: K) {
        val item =  type as OrderDetailsResModel
        MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerOrderSummaryActivity>(requireContext(), Constant.ORDER_SUMMERY_KEY,
            item.id)
    }

    override fun getInflaterView(parent: ViewGroup, viewType: Int): OrderListBaseAdapter.ViewHolder {
        return OrderListBaseAdapter.ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.customer_order_status_adapter, parent, false)
        )
    }


}
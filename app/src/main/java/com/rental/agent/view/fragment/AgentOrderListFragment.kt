package com.rental.agent.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rental.Constant
import com.rental.R
import com.rental.agent.view.activity.AgentOrderSummaryActivity
import com.rental.agent.view.adapter.AgentOrderStatusAdapter
import com.rental.appbiz.AppBizLogger
import com.rental.common.view.fragment.OrderListFragment
import com.rental.customer.dashboard.model.modelclass.CustomerOrderListResModel
import com.rental.customer.dashboard.model.modelclass.CustomerOrderListResModelItem
import com.rental.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.fragment_order_list_tamplate.*

class AgentOrderListFragment : OrderListFragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_agent_order, container, false)

        viewVisibility(view)
        return view
    }

    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        rec_order.adapter= AgentOrderStatusAdapter(
            data as CustomerOrderListResModel,
            requireActivity(),
            this
        )
    }
    override fun <T, K> onViewClick(type: T, where: K) {
        val item =  type as CustomerOrderListResModelItem
        MoveToAnotherComponent.moveToActivity<AgentOrderSummaryActivity>(requireContext(), Constant.ORDER_SUMMERY_KEY,
            item.id)
    }

}



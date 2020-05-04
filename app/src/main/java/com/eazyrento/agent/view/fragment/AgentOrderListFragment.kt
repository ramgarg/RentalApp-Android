package com.eazyrento.agent.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.view.activity.AgentOrderSummaryActivity
import com.eazyrento.common.view.adapter.OrderListBaseAdapter
import com.eazyrento.common.view.fragment.OrderListFragment
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderListResModelItem
import com.eazyrento.customer.utils.MoveToAnotherComponent

class AgentOrderListFragment : OrderListFragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_agent_order, container, false)

        viewVisibility(view)
        return view
    }

    /*override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        rec_order.adapter=
            AgentOrderStatusAdapter(
                data as CustomerOrderListResModel,
                requireActivity(),
                this
            )
    }*/
    override fun <T, K> onViewClick(type: T, where: K) {
        val item =  type as CustomerOrderListResModelItem
        MoveToAnotherComponent.moveToActivityWithIntentValue<AgentOrderSummaryActivity>(requireContext(), Constant.ORDER_SUMMERY_KEY,
            item.id)
    }

    override fun getInflaterView(parent: ViewGroup, viewType: Int): OrderListBaseAdapter.ViewHolder {
        return OrderListBaseAdapter.ViewHolder(LayoutInflater.from(context
            ).inflate(R.layout.agent_order_status_adapter, parent, false)
        )
    }

}



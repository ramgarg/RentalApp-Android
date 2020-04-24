package com.eazyrento.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.fragment.OrderListFragment
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderListResModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderListResModelItem
import com.eazyrento.customer.dashboard.view.activity.CustomerOrderSummaryActivity
import com.eazyrento.customer.dashboard.view.adapter.CustomerOrderStatusAdapter
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.fragment_order_list_tamplate.*

class CustomerOrderListFragment : OrderListFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val  view=inflater.inflate(R.layout.fragment_customer_order, container, false)

        viewVisibility(view)

        return  view
    }

    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        rec_order.adapter=
            CustomerOrderStatusAdapter(
                data as CustomerOrderListResModel,
                requireActivity(),
                this
            )

    }

    override fun <T, K> onViewClick(type: T, where: K) {
        val item =  type as CustomerOrderListResModelItem
        MoveToAnotherComponent.moveToActivity<CustomerOrderSummaryActivity>(requireContext(), Constant.ORDER_SUMMERY_KEY,
            item.id)
    }
}
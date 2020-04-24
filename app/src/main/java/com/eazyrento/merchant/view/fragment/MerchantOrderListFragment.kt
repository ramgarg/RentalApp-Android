package com.eazyrento.merchant.view.fragment

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
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.merchant.view.activity.MerchantOrderSummaryActivity
import com.eazyrento.merchant.view.adapter.MerchantOrderStatusAdapter
import kotlinx.android.synthetic.main.fragment_order_list_tamplate.*

class MerchantOrderListFragment : OrderListFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_merchant_order, container, false)

        viewVisibility(view)
        return view
        }

    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        rec_order.adapter=
            MerchantOrderStatusAdapter(
                data as CustomerOrderListResModel,
                requireActivity(),
                this
            )

    }

    override fun <T, K> onViewClick(type: T, where: K) {
        val item =  type as CustomerOrderListResModelItem
        MoveToAnotherComponent.moveToActivity<MerchantOrderSummaryActivity>(requireContext(), Constant.ORDER_SUMMERY_KEY,
            item.id)
    }

    }

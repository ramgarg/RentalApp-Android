package com.eazyrento.merchant.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.view.adapter.OrderListBaseAdapter
import com.eazyrento.common.view.fragment.OrderListFragment
import com.eazyrento.customer.dashboard.model.modelclass.OrderDetailsResModel
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.merchant.view.activity.MerchantOrderSummaryActivity

class MerchantOrderListFragment : OrderListFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_merchant_order, container, false)

        viewVisibility(view)
        return view
        }

    override fun <T, K> onViewClick(type: T, where: K) {
        val item =  type as OrderDetailsResModel
        MoveToAnotherComponent.moveToActivityWithIntentValue<MerchantOrderSummaryActivity>(requireContext(), Constant.ORDER_SUMMERY_KEY,
            item.id)
    }

    override fun getInflaterView(parent: ViewGroup, viewType: Int): OrderListBaseAdapter.ViewHolder {
        return OrderListBaseAdapter.ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.merchant_order_status_adapter, parent, false)
        )
       }

    override fun setDataHolderBinder(holder: OrderListBaseAdapter.ViewHolder, position: Int) {
        super.setDataHolderBinder(holder, position)

        val item = listOrderItems[position]

        holder.tvOrderProductName?.text=item.merchant_order_detail?.product_name
        holder.tvBookingPrice?.text= Constant.CURRENCY_SIGN.plus(Common.roundOfDouble(item.merchant_order_detail?.booking_price))
        holder.tvOrderQuantity?.text=item.merchant_order_detail?.product_name.plus("-").plus(item.merchant_order_detail?.merchant_quantity)
        
    }

  }

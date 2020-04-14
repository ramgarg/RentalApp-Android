package com.rental.merchant.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rental.R
import com.rental.common.view.fragment.OrderListFragment

class MerchantOrderListFragment : OrderListFragment() {

//    private lateinit var merchant_orderViewModel: MerchantOrderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       // return super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_merchant_order, container, false)

        viewVisibility(view)
        return view
    }
    /*override fun onItemClick(item: Data) {
        MoveToAnotherComponent.moveToMerchantOrderSummaryActivity(requireContext())
    }*/

    /*private fun viewVisibility(view: View){


        view.merchant_layout_open_active.setOnClickListener {

            Common.showGroupViews(merchant_layout_open_active,merchant_layout_close_inactive)
            Common.hideGroupViews(merchant_layout_open_inactive,merchant_layout_close_active)
            EventBus.getDefault().postSticky("OPEN_ACTIVE")
            this.merchant_orderViewModel.getOrderResponse().observe(this, Observer {
                merchant_rec_order.adapter= MerchantOrderOpenAdapter(it.data,requireActivity(),this)
            })
        }

        view.merchant_layout_close_inactive.setOnClickListener {
            Common.showGroupViews(merchant_layout_open_inactive,merchant_layout_close_active)
            Common.hideGroupViews(merchant_layout_close_inactive,merchant_layout_open_active)
            EventBus.getDefault().postSticky("CLOSE_ACTIVE")
            this.merchant_orderViewModel.getOrderResponse().observe(this, Observer {
                merchant_rec_order.adapter= MerchantOrderCloseAdapter(it.data,requireActivity(),this)
            })

        }
    }*/


    }

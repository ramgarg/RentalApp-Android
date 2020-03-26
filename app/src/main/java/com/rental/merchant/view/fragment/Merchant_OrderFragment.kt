package com.rental.merchant.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.dashboard.view.activity.MainActivity
import com.rental.customer.dashboard.view.adapter.OrderClosedAdapter
import com.rental.customer.dashboard.view.adapter.OrderOpenAdapter
import com.rental.customer.dashboard.viewmodel.OrderViewModel
import com.rental.customer.utils.Common
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.RecyclerViewItemClick
import com.rental.merchant.view.activity.Merchant_MainActivity
import com.rental.merchant.viewModel.MerchantOrderViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.android.synthetic.main.fragment_order.view.*
import kotlinx.android.synthetic.main.merchant_activity_main.*
import kotlinx.android.synthetic.main.merchant_orderfragment.*
import kotlinx.android.synthetic.main.merchant_orderfragment.view.*
import org.greenrobot.eventbus.EventBus

class Merchant_OrderFragment : Fragment() , RecyclerViewItemClick {

    private lateinit var merchant_orderViewModel: MerchantOrderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.merchant_orderfragment, container, false)

        viewVisibility(view)

        merchant_orderViewModel = ViewModelProviders.of(this).get(MerchantOrderViewModel::class.java)
        merchant_orderViewModel.getOrderResponse().observe(this, Observer {
            merchant_rec_order.adapter = OrderOpenAdapter(it.data, requireActivity(), this)
            EventBus.getDefault().postSticky("OPEN_ACTIVE")
            (activity as Merchant_MainActivity).merchant_layout_loading.visibility = View.GONE
        })
        return view
    }
    override fun onItemClick(item: Data) {
        MoveToAnotherComponent.moveToOrderSummaryActivity(requireContext())
    }

    @SuppressLint("RestrictedApi")
    private fun viewVisibility(view: View){


        view. merchant_layout_open_active.setOnClickListener {

            Common.showGroupViews(merchant_layout_open_active,merchant_layout_close_inactive)
            Common.hideGroupViews(merchant_layout_open_inactive,merchant_layout_close_active)
            EventBus.getDefault().postSticky("OPEN_ACTIVE")
            this.merchant_orderViewModel.getOrderResponse().observe(this, Observer {
                merchant_rec_order.adapter= OrderOpenAdapter(it.data,requireActivity(),this)
            })
        }

        view. merchant_layout_close_inactive.setOnClickListener {
            Common.showGroupViews(merchant_layout_open_inactive,merchant_layout_close_active)
            Common.hideGroupViews(merchant_layout_close_inactive,merchant_layout_open_active)
            EventBus.getDefault().postSticky("CLOSE_ACTIVE")
            this.merchant_orderViewModel.getOrderResponse().observe(this, Observer {
                merchant_rec_order.adapter= OrderClosedAdapter(it.data,requireActivity(),this)
            })

        }
    }
    }

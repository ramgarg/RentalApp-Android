package com.rental.customer.dashboard.view.fragment

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
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.RecyclerViewItemClick
import com.rental.customer.utils.Common
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.android.synthetic.main.fragment_order.view.*
import org.greenrobot.eventbus.EventBus

open class OrderFragment : Fragment() ,RecyclerViewItemClick {

   private lateinit var orderViewModel: OrderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val  view=inflater.inflate(R.layout.fragment_order, container, false)

        viewVisibility(view)

        orderViewModel= ViewModelProviders.of(this).get(OrderViewModel::class.java)
        orderViewModel.getOrderResponse().observe(this, Observer {
            rec_order.adapter= OrderOpenAdapter(it.data,requireActivity(),this)
            EventBus.getDefault().postSticky("OPEN_ACTIVE")
//            (activity as MainActivity).layout_loading.visibility=View.GONE
        })
        return  view
    }

    override fun onItemClick(item: Data) {
    MoveToAnotherComponent.moveToOrderSummaryActivity(requireContext())
    }

    private fun viewVisibility(view: View){


       view. layout_open_inactive.setOnClickListener {

           Common.showGroupViews(layout_open_active,layout_close_inactive)
           Common.hideGroupViews(layout_open_inactive,layout_close_active)
           EventBus.getDefault().postSticky("OPEN_ACTIVE")
            this.orderViewModel.getOrderResponse().observe(this, Observer {
                rec_order.adapter= OrderOpenAdapter(it.data,requireActivity(),this)
            })
        }

        view. layout_close_inactive.setOnClickListener {
            Common.showGroupViews(layout_open_inactive,layout_close_active)
            Common.hideGroupViews(layout_close_inactive,layout_open_active)
            EventBus.getDefault().postSticky("CLOSE_ACTIVE")
            this.orderViewModel.getOrderResponse().observe(this, Observer {
                rec_order.adapter= OrderClosedAdapter(it.data,requireActivity(),this)
            })

        }
    }


}

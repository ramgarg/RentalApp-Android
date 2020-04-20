package com.rental.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rental.R
import com.rental.agent.view.adapter.RecycleAdapterAgentBookings
import com.rental.common.model.modelclass.Order_listing
import com.rental.common.view.fragment.BaseFragment
import com.rental.common.viewmodel.OrderListingVM
import com.rental.customer.dashboard.view.adapter.RecycleAdapterCustomerBookings
import kotlinx.android.synthetic.main.fragment_agent_bookings.*
import kotlinx.android.synthetic.main.fragment_customer_bookings.*

class CustomerBookingsFragment: BaseFragment() {

    private lateinit var orderListingVM: OrderListingVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_customer_bookings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        orderListingVM = ViewModelProviders.of(this).get(OrderListingVM::class.java)


        orderListingVM.orderListingLiveData.observe(viewLifecycleOwner, Observer {

            rec_customer_bookings.layoutManager = LinearLayoutManager(requireActivity(),
                LinearLayoutManager.VERTICAL,false)
            (rec_customer_bookings.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(1,1)

            val recyleAdapterCustomerBookings= RecycleAdapterCustomerBookings(it.order_listing as MutableList<Order_listing>, requireActivity())

            rec_customer_bookings.adapter = recyleAdapterCustomerBookings

        })
    }
}
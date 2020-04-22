package com.rental.agent.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rental.Constant
import com.rental.R
import com.rental.agent.view.AgentBaseFragment
import com.rental.agent.view.adapter.RecycleAdapterAgentBookings
import com.rental.common.model.modelclass.Order_listing
import com.rental.common.view.fragment.MyBookingBaseFragment
import com.rental.common.viewmodel.OrderListingVM
import kotlinx.android.synthetic.main.fragment_agent_bookings.*

class AgentBookingsFragment : MyBookingBaseFragment() {

//    private lateinit var orderListingVM: OrderListingVM

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
           return super.onCreateView(inflater, container, savedInstanceState)
       /* val view = inflater.inflate(R.layout.fragment_agent_bookings, container, false)
        return view*/

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        callMyBookingAPI(Constant.BOOKING_LIST_AGENT)

      /*  orderListingVM = ViewModelProviders.of(this).get(OrderListingVM::class.java)


        orderListingVM.orderListingLiveData.observe(viewLifecycleOwner, Observer {

            rec_agent_bookings.layoutManager = LinearLayoutManager(requireActivity(),
                LinearLayoutManager.VERTICAL,false)
            (rec_agent_bookings.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(1,1)

            val recyleAdapterAgentBookings= RecycleAdapterAgentBookings(it.order_listing as MutableList<Order_listing>, requireActivity())

            rec_agent_bookings.adapter = recyleAdapterAgentBookings

        })*/
    }




}



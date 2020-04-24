package com.eazyrento.agent.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eazyrento.Constant
import com.eazyrento.common.view.fragment.MyBookingBaseFragment

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



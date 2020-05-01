package com.eazyrento.agent.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eazyrento.Constant
import com.eazyrento.common.view.fragment.MyBookingBaseFragment

class AgentBookingsFragment : MyBookingBaseFragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
           return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        callMyBookingAPI(Constant.BOOKING_LIST_AGENT)
    }




}



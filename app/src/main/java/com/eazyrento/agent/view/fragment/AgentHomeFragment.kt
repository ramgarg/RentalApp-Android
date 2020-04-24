package com.eazyrento.agent.view.fragment

import android.os.Bundle
import com.eazyrento.Constant
import com.eazyrento.common.view.fragment.DashboardBaseFragment

class AgentHomeFragment : DashboardBaseFragment() {

//    private lateinit var orderListingVM: OrderListingVM

   /* override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_booking_dashboard, container, false)

        return view
    }*/

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        callAPIDashboard(Constant.BOOKING_DASHBOARD_AGENT)
    }

}



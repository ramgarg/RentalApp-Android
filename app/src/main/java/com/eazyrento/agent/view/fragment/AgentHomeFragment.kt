package com.eazyrento.agent.view.fragment

import android.os.Bundle
import androidx.core.view.GravityCompat
import com.eazyrento.Constant
import com.eazyrento.common.view.fragment.DashboardBaseFragment
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_agent_home_.*
import kotlinx.android.synthetic.main.booking_dashboard_adapter_view.*
import kotlinx.android.synthetic.main.toolbar.*

class AgentHomeFragment : DashboardBaseFragment() {

//    private lateinit var orderListingVM: OrderListingVM

   /* override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_booking_dashboard, container, false)

        return view
    }*/

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        callAPIDashboard(Constant.BOOKING_DASHBOARD_AGENT)

        //img_menu.setOnClickListener {  drawer_layout_agent.openDrawer(GravityCompat.START) }

    }

}



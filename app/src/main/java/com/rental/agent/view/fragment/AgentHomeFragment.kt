package com.rental.agent.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rental.R
import com.rental.ValidationMessage
import com.rental.agent.model.modelclass.AgentDashboardResModel
import com.rental.agent.view.AgentBaseFragment
import com.rental.agent.viewmodel.AgentDashboardViewModel
import com.rental.appbiz.AppBizLogger
import com.rental.customer.utils.Common
import com.rental.merchant.model.modelclass.Booking
import com.rental.common.view.adapter.DashboardBookingCardAdapter
import com.rental.common.view.fragment.DashboardBaseFragment
import kotlinx.android.synthetic.main.fragment_agent_dashboard.*
import kotlinx.android.synthetic.main.merchant_fragment_dash.*

class AgentHomeFragment : DashboardBaseFragment() {

//    private lateinit var orderListingVM: OrderListingVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_agent_dashboard, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
    }

}



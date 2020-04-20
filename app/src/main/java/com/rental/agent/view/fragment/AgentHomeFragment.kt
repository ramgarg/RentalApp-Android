package com.rental.agent.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rental.R
import com.rental.common.view.fragment.DashboardBaseFragment

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



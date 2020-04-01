package com.rental.agent.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rental.R
import com.rental.agent.view.AgentBaseFragment
import com.rental.agent.view.CustomViewOutlineProvider
import com.rental.agent.view.adapter.RecyleAdapterAgentHomeCard
import com.rental.common.model.modelclass.Order_listing
import com.rental.common.viewmodel.OrderListingViewModel
import kotlinx.android.synthetic.main.fragment_agent_dashboard.*

class AgentHomeFragment : AgentBaseFragment() {

    private lateinit var orderListingViewModel: OrderListingViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_agent_dashboard, container, false)



        //activity?.findViewById<View>(R.id.drawer_layout_agent)?.visibility = View.GONE

        orderListingViewModel = ViewModelProviders.of(this).get(OrderListingViewModel::class.java)


        orderListingViewModel.getAgentHomeOrderList().observe(this, Observer {

            recycle_view_agent_home.layoutManager = LinearLayoutManager(requireActivity(),
                LinearLayoutManager.HORIZONTAL,false)
            (recycle_view_agent_home.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(1,1)

            val recyleAdapterAgentHomeCard= RecyleAdapterAgentHomeCard(it.order_listing as MutableList<Order_listing>, requireActivity())

            recycle_view_agent_home.adapter = recyleAdapterAgentHomeCard

        })

        return view
    }

    private fun cirleViewOutline() {
        btn_agent_home_view_all.outlineProvider = CustomViewOutlineProvider()
        btn_agent_home_view_all.clipToOutline = true
    }


}



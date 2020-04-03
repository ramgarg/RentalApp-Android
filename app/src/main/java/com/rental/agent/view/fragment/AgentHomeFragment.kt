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
import com.rental.common.viewmodel.OrderListingVM
import kotlinx.android.synthetic.main.fragment_agent_dashboard.*

class AgentHomeFragment : AgentBaseFragment() {

    private lateinit var orderListingVM: OrderListingVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_agent_dashboard, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        orderListingVM = ViewModelProviders.of(this).get(OrderListingVM::class.java)


        orderListingVM.orderListingLiveData.observe(viewLifecycleOwner, Observer {

            recycle_view_agent_home.layoutManager = LinearLayoutManager(requireActivity(),
                LinearLayoutManager.HORIZONTAL,false)
            (recycle_view_agent_home.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(1,1)

            val recyleAdapterAgentHomeCard= RecyleAdapterAgentHomeCard(it.order_listing as MutableList<Order_listing>, requireActivity())

            recycle_view_agent_home.adapter = recyleAdapterAgentHomeCard

        })
    }

    private fun cirleViewOutline() {
        btn_agent_home_view_all.outlineProvider = CustomViewOutlineProvider()
        btn_agent_home_view_all.clipToOutline = true
    }


}



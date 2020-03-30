package com.rental.agent.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rental.R
import com.rental.agent.view.adapter.RecyleAdapterAgentHomeCard
import com.rental.common.model.modelclass.Order_listing
import com.rental.common.viewmodel.AgentHomeViewModel
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.RecyclerViewItemClick
import kotlinx.android.synthetic.main.fragment_agent_dashboard.*

class AgentHomeFragment : Fragment(), RecyclerViewItemClick {

    private lateinit var agentHomeViewModel: AgentHomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_agent_dashboard, container, false)

        agentHomeViewModel = ViewModelProviders.of(this).get(AgentHomeViewModel::class.java)

        agentHomeViewModel.getAgentHomeOrderList().observe(this, Observer {

            recycle_view_agent_home.layoutManager = LinearLayoutManager(requireActivity(),
                LinearLayoutManager.HORIZONTAL,false)
            (recycle_view_agent_home.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(1,1)

            val recyleAdapterAgentHomeCard= RecyleAdapterAgentHomeCard(it.order_listing as MutableList<Order_listing>, requireActivity())

            recycle_view_agent_home.adapter = recyleAdapterAgentHomeCard

        })

        return view
    }

    override fun onItemClick(item: Data) {
        MoveToAnotherComponent.moveToCategoryActivity(requireContext())
    }


}



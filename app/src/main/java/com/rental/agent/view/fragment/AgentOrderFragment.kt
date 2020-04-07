package com.rental.agent.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.agent.view.AgentOrderViewModel
import com.rental.agent.view.adapter.AgentCloseOrderAdapter
import com.rental.agent.view.adapter.AgentOpenOrderAdapter
import com.rental.common.model.modelclass.MasterResModelItem
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.utils.Common
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.RecyclerViewItemClick
import kotlinx.android.synthetic.main.fragment_agent_order.*
import kotlinx.android.synthetic.main.fragment_agent_order.view.*
import org.greenrobot.eventbus.EventBus

class AgentOrderFragment : Fragment() , RecyclerViewItemClick{

    private lateinit var agent_orderViewModel: AgentOrderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_agent_order, container, false)

        viewVisibility(view)

        agent_orderViewModel = ViewModelProviders.of(this).get(AgentOrderViewModel::class.java)
        agent_orderViewModel.getOrderResponse().observe(this, Observer {
            agent_rec_order.adapter = AgentOpenOrderAdapter(it.data, requireActivity(), this)
            EventBus.getDefault().postSticky("OPEN_ACTIVE")
        })
        return view
    }
    override fun onItemClick(item: Data) {
        MoveToAnotherComponent.moveToOrderSummaryActivity(requireContext())
    }

    private fun viewVisibility(view: View){


        view. agent_layout_open_active.setOnClickListener {

            Common.showGroupViews(agent_layout_open_active,agent_layout_close_inactive)
            Common.hideGroupViews(agent_layout_open_inactive,agent_layout_close_active)
            EventBus.getDefault().postSticky("OPEN_ACTIVE")
            this.agent_orderViewModel.getOrderResponse().observe(this, Observer {
                agent_rec_order.adapter= AgentOpenOrderAdapter(it.data,requireActivity(),this)
            })
        }

        view. agent_layout_close_inactive.setOnClickListener {
            Common.showGroupViews(agent_layout_open_inactive,agent_layout_close_active)
            Common.hideGroupViews(agent_layout_close_inactive,agent_layout_open_active)
            EventBus.getDefault().postSticky("CLOSE_ACTIVE")
            this.agent_orderViewModel.getOrderResponse().observe(this, Observer {
                agent_rec_order.adapter= AgentCloseOrderAdapter(it.data,requireActivity(),this)
            })

        }
    }

}



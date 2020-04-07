package com.rental.agent.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rental.R
import com.rental.common.model.modelclass.MasterResModelItem
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.dashboard.view.fragment.ProfieFragment
import com.rental.customer.dashboard.viewmodel.CustomerHomeViewModel
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.RecyclerViewItemClick

class AgentProfileFragment : ProfieFragment(), RecyclerViewItemClick {

    private lateinit var customerHomeViewModel: CustomerHomeViewModel

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_agent_profile, container, false)
        return view

    }

    override fun onItemClick(item: Data) {
        MoveToAnotherComponent.moveToCategoryActivity(requireContext())
    }


}



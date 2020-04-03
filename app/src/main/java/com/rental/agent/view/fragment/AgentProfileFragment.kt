package com.rental.agent.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.agent.view.activity.AgentHomeActivity
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.dashboard.view.activity.MainActivity
import com.rental.customer.dashboard.view.adapter.HomeAdapter
import com.rental.customer.dashboard.view.fragment.ProfieFragment
import com.rental.customer.dashboard.viewmodel.HomeViewModel
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.RecyclerViewItemClick
import com.rental.merchant.view.activity.MerchantMainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.merchant_activity_main.*

class AgentProfileFragment : ProfieFragment(), RecyclerViewItemClick {

    private lateinit var homeViewModel: HomeViewModel

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_agent_profile, container, false)
        return view

    }

    override fun onItemClick(item: Data) {
        MoveToAnotherComponent.moveToCategoryActivity(requireContext())
    }


}



package com.rental.agent.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.RecyclerViewItemClick

class AgentNotificationFragment : Fragment(), RecyclerViewItemClick {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_agent_notification, container, false)

        /*homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        homeViewModel.getHomeResponse().observe(this, Observer {
                rec_veichle.adapter = HomeAdapter(it.data as ArrayList<Data>, requireActivity(), this@AgentHomeFragment)

                (activity as MainActivity).layout_loading.visibility=View.GONE

        })*/

        return view
    }

    override fun onItemClick(item: Data) {
        MoveToAnotherComponent.moveToCategoryActivity(requireContext())
    }



}



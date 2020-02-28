package com.rental.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.dashboard.view.adapter.HomeAdapter
import com.rental.customer.dashboard.viewmodel.HomeViewModel
import com.rental.customer.utils.RecyclerViewItemClick
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment :Fragment() , RecyclerViewItemClick {

    val paymentHistoryList: ArrayList<Data> = ArrayList()
    lateinit var homeViewModel:HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      val view=  inflater.inflate(R.layout.fragment_home, container, false)

        homeViewModel=ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeViewModel.getHomeResponse().observe(this, Observer {
            rec_veichle.adapter= HomeAdapter(it.data,requireActivity(),this)
        })

        return view
    }

    override fun onItemClick(item: Data) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        val fragment = CategoryFragment()
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment,
            fragment.javaClass.getSimpleName())
            .addToBackStack(null)
            .commit()
    }


}



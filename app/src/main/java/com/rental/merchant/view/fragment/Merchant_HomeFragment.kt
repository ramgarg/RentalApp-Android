package com.rental.merchant.view.fragment

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
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.RecyclerViewItemClick
import com.rental.merchant.view.activity.Merchant_MainActivity
import com.rental.merchant.viewModel.MerchantHomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.merchant_activity_main.*

class Merchant_HomeFragment : Fragment(), RecyclerViewItemClick {
    private lateinit var merchant_homeViewModel: MerchantHomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_merchant_home, container, false)



        merchant_homeViewModel = ViewModelProviders.of(this).get(MerchantHomeViewModel::class.java)
        merchant_homeViewModel.getmerchant_HomeResponse().observe(this, Observer {
            rec_veichle.adapter = HomeAdapter(it.data as ArrayList<Data>, requireActivity(), this)
            (activity as Merchant_MainActivity).merchant_layout_loading.visibility= View.GONE

        })

        return view
    }

    override fun onItemClick(item: Data) {
        MoveToAnotherComponent.moveToCategoryActivity(requireContext())
    }
}
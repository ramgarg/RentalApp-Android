package com.rental.merchant.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rental.R
import com.rental.common.model.modelclass.Order_listing
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.RecyclerViewItemClick
import com.rental.merchant.view.adapter.RecycleAdapterMerchantHomeCard
import com.rental.merchant.viewModel.MerchantDashViewModel
import kotlinx.android.synthetic.main.merchant_fragment_dash.*

class MerchantDashFragment : Fragment(), RecyclerViewItemClick {

    private lateinit var merchantDashViewModel: MerchantDashViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.merchant_fragment_dash, container, false)

        merchantDashViewModel = ViewModelProviders.of(this).get(MerchantDashViewModel::class.java)

        merchantDashViewModel.getmerchantHomeOrderList().observe(this, Observer {

            recycle_view_merchant_home.layoutManager = LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.HORIZONTAL, false
            )
            (recycle_view_merchant_home.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                1,
                1
            )

            val recycleAdapterMerchantHomeCard = RecycleAdapterMerchantHomeCard(
                it.order_listing as MutableList<Order_listing>,
                requireActivity()
            )

            recycle_view_merchant_home.adapter = recycleAdapterMerchantHomeCard

        })

        return view
    }
    override fun onItemClick(item: Data) {
        MoveToAnotherComponent.moveToCategoryActivity(requireContext())
    }

}
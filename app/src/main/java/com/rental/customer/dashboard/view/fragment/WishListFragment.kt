package com.rental.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rental.R
import com.rental.customer.dashboard.view.activity.CustomerMainActivity
import com.rental.customer.dashboard.view.adapter.WishListAdapter
import com.rental.customer.dashboard.viewmodel.WishListViewModel
import com.rental.customer.utils.Common
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_wish_list.view.*

class WishListFragment :Fragment() {

    private lateinit var wishListViewModel: WishListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wish_list, container, false)


        //when data come form api use this code
//        wishListViewModel= ViewModelProviders.of(this).get(WishListViewModel::class.java)
//        wishListViewModel.getWishListResponse().observe(this, Observer {
//            rec_wishlist.adapter= WishListAdapter(it.data as ArrayList<Data>,requireActivity())
//        })

        //This is for testing
        view.rec_wishlist.adapter = WishListAdapter(Common.wishListModel,requireActivity())
        (activity as CustomerMainActivity).layout_loading.visibility=View.GONE

        return view
    }

}
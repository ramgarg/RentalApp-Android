package com.rental.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.customer.dashboard.view.adapter.WishListAdapter
import com.rental.customer.dashboard.viewmodel.WishListViewModel
import kotlinx.android.synthetic.main.fragment_wish_list.*

class WishListFragment :Fragment() {

    lateinit var wishListViewModel: WishListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wish_list, container, false)

        wishListViewModel= ViewModelProviders.of(this).get(WishListViewModel::class.java)
        wishListViewModel.getWishListResponse().observe(this, Observer {
            rec_wishlist.adapter= WishListAdapter(it.data,requireActivity())
        })

        return view
    }
}
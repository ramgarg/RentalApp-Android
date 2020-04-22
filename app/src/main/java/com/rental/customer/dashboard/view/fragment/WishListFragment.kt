package com.rental.customer.dashboard.view.fragment

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
import com.rental.common.view.fragment.BaseFragment
import com.rental.common.viewmodel.OrderListingVM
import com.rental.customer.dashboard.model.modelclass.CustomerWishListResModel
import com.rental.customer.dashboard.model.modelclass.WishListItem
import com.rental.customer.dashboard.view.activity.CustomerMainActivity
import com.rental.customer.dashboard.view.adapter.RecycleAdapterCustomerBookings
import com.rental.customer.dashboard.view.adapter.WishListAdapter
import com.rental.customer.dashboard.viewmodel.CustomerWishListViewModel
import com.rental.customer.dashboard.viewmodel.WishListViewModel
import com.rental.customer.utils.Common
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_customer_bookings.*
import kotlinx.android.synthetic.main.fragment_wish_list.*
import kotlinx.android.synthetic.main.fragment_wish_list.view.*

class WishListFragment : BaseFragment() {

    private lateinit var orderListingVM: OrderListingVM


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wish_list, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIFragment<CustomerWishListViewModel>(this).getWishList()
                , viewLifecycleOwner, requireActivity()
            )
        }

        /*orderListingVM = ViewModelProviders.of(this).get(OrderListingVM::class.java)


        orderListingVM.orderListingLiveData.observe(viewLifecycleOwner, Observer {

            rec_wishlist.layoutManager = LinearLayoutManager(requireActivity(),
                LinearLayoutManager.VERTICAL,false)
            (rec_wishlist.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(1,1)

            val recyleAdapterWishlist= WishListAdapter(it.order_listing as MutableList<Order_listing>, requireActivity())

            rec_wishlist.adapter = recyleAdapterWishlist

        })*/
    }

    override fun <T> onSuccessApiResult(data: T) {
        val wishListRes = data as CustomerWishListResModel

        rec_wishlist.layoutManager = LinearLayoutManager(requireActivity(),
            LinearLayoutManager.VERTICAL,false)
        (rec_wishlist.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(1,1)

        val recyleAdapterWishlist= WishListAdapter(wishListRes as MutableList<WishListItem>, requireActivity())

        rec_wishlist.adapter = recyleAdapterWishlist
    }


    }


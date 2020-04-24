package com.eazyrento.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazyrento.R
import com.eazyrento.common.view.fragment.BaseFragment
import com.eazyrento.common.viewmodel.OrderListingVM
import com.eazyrento.customer.dashboard.model.modelclass.CustomerWishListResModel
import com.eazyrento.customer.dashboard.model.modelclass.WishListItem
import com.eazyrento.customer.dashboard.view.adapter.WishListAdapter
import com.eazyrento.customer.dashboard.viewmodel.CustomerWishListViewModel
import kotlinx.android.synthetic.main.fragment_wish_list.*

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

        val recyleAdapterWishlist=
            WishListAdapter(
                wishListRes as MutableList<WishListItem>,
                requireActivity()
            )

        rec_wishlist.adapter = recyleAdapterWishlist
    }


    }


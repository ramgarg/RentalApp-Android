package com.eazyrento.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.view.fragment.BaseFragment
import com.eazyrento.customer.dashboard.model.modelclass.CustomerWishListResModel
import com.eazyrento.customer.dashboard.model.modelclass.WishListItem
import com.eazyrento.customer.dashboard.view.activity.ProductDetailsActivity
import com.eazyrento.customer.dashboard.view.adapter.WishListAdapter
import com.eazyrento.customer.dashboard.viewmodel.CustomerWishDeleteViewModel
import com.eazyrento.customer.dashboard.viewmodel.CustomerWishListViewModel
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.fragment_wish_list.*

class WishListFragment : BaseFragment() {

    var listPosition:Int =0
    lateinit var listWish:MutableList<WishListItem>

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

    }

    override fun <T> onSuccessApiResult(data: T) {

        if( data is CustomerWishListResModel){
            listWish = data

        rec_wishlist.layoutManager = LinearLayoutManager(requireActivity(),
            LinearLayoutManager.VERTICAL,false)
        (rec_wishlist.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(1,1)

        val recyleAdapterWishlist=
            WishListAdapter(
                listWish,
                requireActivity(),this
            )

        rec_wishlist.adapter = recyleAdapterWishlist
        }
        else{
            if (data is JsonElement){
               listWish.removeAt(listPosition)
               rec_wishlist.adapter?.notifyDataSetChanged()
            }
        }
    }


     fun delete(wishListItem: WishListItem,pos:Int) {
         listPosition = pos
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIFragment<CustomerWishDeleteViewModel>(this)
                    .wishDelete(wishListItem.id)
                , viewLifecycleOwner, requireActivity()
            )
        }
    }

     fun viewDetails(wishListItem: WishListItem,pos: Int) {
         listPosition = pos
         MoveToAnotherComponent.moveToActivity<ProductDetailsActivity>(requireContext(),
             Constant.VEHICLES_SUB_CATE,wishListItem.id
         )
    }

    override fun <T, K> onViewClick(type: T, where: K) {
    }


    }


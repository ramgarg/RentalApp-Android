package com.eazyrento.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.view.BaseNavigationActivity
import com.eazyrento.appbiz.amountWithCurrencyName
import com.eazyrento.common.view.fragment.BaseFragment
import com.eazyrento.customer.dashboard.model.modelclass.CustomerWishListResModel
import com.eazyrento.customer.dashboard.model.modelclass.WishListItem
import com.eazyrento.customer.dashboard.view.activity.CustomerMainActivity
import com.eazyrento.customer.dashboard.view.activity.ProductDetailsActivity
import com.eazyrento.customer.dashboard.view.adapter.DeleteAndViewDetails
import com.eazyrento.customer.dashboard.view.adapter.WishListAdapter
import com.eazyrento.customer.dashboard.viewmodel.CustomerWishDeleteViewModel
import com.eazyrento.customer.dashboard.viewmodel.CustomerWishListViewModel
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.fragment_wish_list.*

class WishListFragment : BaseFragment(), DeleteAndViewDetails {

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

            if (data.isEmpty())
            {
                Common.showToast(requireContext(),R.string.NO_DATA_FOUND)

                return
            }

            listWish = data

        rec_wishlist.layoutManager = LinearLayoutManager(requireActivity(),
            LinearLayoutManager.VERTICAL,false)

//        (rec_wishlist.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(1,1)

        val recyleAdapterWishlist=
            WishListAdapter(
                listWish,
                requireActivity(),this
            )

        rec_wishlist.adapter = recyleAdapterWishlist
        }
        else{
            if (data is JsonElement){
                if(listWish.size>0) {
                    listWish.removeAt(listPosition)
                    rec_wishlist.adapter?.notifyDataSetChanged()

                    // count badge icon from wishlist
                        if(activity is CustomerMainActivity){
                            (activity as CustomerMainActivity).badgeOnBottomNavigationView(listWish.size)
                        }

                }
            }
        }
    }


      fun <T>delete(wishListItem: T,pos:Int) {
         if(wishListItem is WishListItem) {
             listPosition = pos
             callAPI()?.let {
                 it.observeApiResult(
                     it.callAPIFragment<CustomerWishDeleteViewModel>(this)
                         .wishDelete(wishListItem.product_id)
                     , viewLifecycleOwner, requireActivity()
                 )
             }
         }
    }

     fun <T>viewDetails(wishListItem: T,pos: Int) {
        if(wishListItem is WishListItem) {
            listPosition = pos
            MoveToAnotherComponent.moveToActivityWithIntentValue<ProductDetailsActivity>(
                requireContext(),
                Constant.VEHICLES_SUB_CATE, wishListItem.product_id
            )
        }
    }

    override fun <T, K> onViewClick(type: T, where: K) {
    }

    override fun setHolderOnView(holder: WishListAdapter.CardViewHolder, position: Int) {

        holder.tv_pro_name?.text=listWish.get(position).product_name.capitalize()
        holder.tv_booking_price?.text= context?.amountWithCurrencyName(listWish.get(position).price)
        holder.lyt_booking_details.visibility = View.GONE

        holder.tv_remove.setOnClickListener{
            delete(listWish.get(position),position)
        }

        holder.tv_view_detail.setOnClickListener{
            viewDetails(listWish.get(position),position)
        }
    }


}


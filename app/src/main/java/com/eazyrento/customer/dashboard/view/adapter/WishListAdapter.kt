package com.eazyrento.customer.dashboard.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.R
import kotlinx.android.synthetic.main.row_wishlist.view.*
import kotlinx.android.synthetic.main.template_product_main_view.view.*
import kotlinx.android.synthetic.main.template_quantity_view.view.*

class WishListAdapter<T> (val orderListing: MutableList<T>, val context: Context,val deleteAndViewDetails: DeleteAndViewDetails) : RecyclerView.Adapter<WishListAdapter.CardViewHolder>()  {


    class CardViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tv_pro_name=view.pro_name
        val tv_booking_price=view.pro_booking_price
        val tv_quantity = view.item_quantity
        val tv_view_detail = view.tv_view_detail
        val pro_booking_days = view.pro_booking_days
        val tv_remove =view.tv_remove
        val lyt_booking_details = view.lyt_booking_details
        val tv_work_location = view.tv_work_location
        val add_quantity = view.add_quantity
        val minus_quantity = view.minus_quantity

    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

        deleteAndViewDetails.setHolderOnView(holder,position)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val card_view =
            CardViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.row_wishlist,
                    parent,
                    false
                )
            )
        return card_view
    }

    override fun getItemCount(): Int {
        return orderListing.size
    }
}

interface DeleteAndViewDetails{

    //fun <T>delete(wishListItem:T,position: Int)
   // fun <T>viewDetails(wishListItem: T,position: Int)
    fun setHolderOnView(holder: WishListAdapter.CardViewHolder, position: Int)
}

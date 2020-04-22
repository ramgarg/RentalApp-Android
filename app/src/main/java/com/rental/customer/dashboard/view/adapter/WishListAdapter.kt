package com.rental.customer.dashboard.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rental.R
import com.rental.common.model.modelclass.Order_listing
import com.rental.customer.dashboard.model.modelclass.WishListItem
import kotlinx.android.synthetic.main.row_customer_bookings.view.*
import kotlinx.android.synthetic.main.row_wishlist.view.*

class WishListAdapter (val orderListing: MutableList<WishListItem>, val context: Context) : RecyclerView.Adapter<WishListAdapter.CardViewHolder>()  {


    class CardViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tv_wish_name=view.tv_wish_name
        val tv_wish_price=view.tv_wish_price

    }

    override fun onBindViewHolder(holder: WishListAdapter.CardViewHolder, position: Int) {

        holder.tv_wish_name?.text=orderListing.get(position).product_name
        holder.tv_wish_price?.text="$"+orderListing.get(position).price


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListAdapter.CardViewHolder {
        val card_view = WishListAdapter.CardViewHolder(
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
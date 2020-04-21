package com.rental.customer.dashboard.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rental.R
import com.rental.common.model.modelclass.Order_listing
import kotlinx.android.synthetic.main.row_customer_bookings.view.*

class WishListAdapter (val orderListing: MutableList<Order_listing>, val context: Context) : RecyclerView.Adapter<WishListAdapter.CardViewHolder>()  {


    class CardViewHolder(view: View):RecyclerView.ViewHolder(view){


    }

    override fun onBindViewHolder(holder: WishListAdapter.CardViewHolder, position: Int) {
        val order_listing_obj =  orderListing.get(position)

        //customer details

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
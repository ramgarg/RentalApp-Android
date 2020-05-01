package com.eazyrento.common.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.model.modelclass.Booking
import com.eazyrento.customer.dashboard.view.adapter.WishListAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_view_orders.view.*

class DashboardBookingCardAdapter (val orderListing: List<Booking>, val context: Context,val acceptDecline:AcceptDecline) : RecyclerView.Adapter<DashboardBookingCardAdapter.CardViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val card_view =
            CardViewHolder(
                LayoutInflater.from(context).inflate(R.layout.card_view_orders, parent, false)
            )
        return card_view
    }

    override fun getItemCount(): Int {
        return orderListing.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        acceptDecline.setBookingHolder(holder,orderListing,position)
        //
    }
   
   
    class CardViewHolder(view: View):RecyclerView.ViewHolder(view){

        
        val tv__name = view.tv__name
        val tv__type = view.tv__type
        val tv__order = view.tv__order
        val tv__product_quantity = view.tv__quantiity
        val tv__date_show = view.tv_show_date

        val img__pic = view.img__pic
        val btn__accept = view.btn__accept
        val btn__decline = view.btn__decline

    }
}

interface AcceptDecline{
    fun setBookingHolder(holder: DashboardBookingCardAdapter.CardViewHolder,list: List<Booking>, position: Int)
}
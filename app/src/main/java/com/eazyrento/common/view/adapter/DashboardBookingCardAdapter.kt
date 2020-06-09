package com.eazyrento.common.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.R
import com.eazyrento.common.model.modelclass.Booking
import com.eazyrento.common.model.modelclass.BookingAdapterModel
import kotlinx.android.synthetic.main.card_view_orders.view.*
import kotlinx.android.synthetic.main.card_view_orders.view.tv_show_date
import kotlinx.android.synthetic.main.phone_view.view.*

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
        acceptDecline.setBookingHolder(holder,orderListing,position,holder.bookingAdapterModel)
        //
    }
   
   
    class CardViewHolder(view: View):RecyclerView.ViewHolder(view){

        val bookingAdapterModel = BookingAdapterModel(view.tv__name,view.tv__type,view.img__pic, view.phone_view,
            view.tv__quantiity,view.tv_show_date,view.tv__order,null,view.btn__accept,view.btn__decline)

    }
}

interface AcceptDecline{
    fun setBookingHolder(holder: DashboardBookingCardAdapter.CardViewHolder,list: List<Booking>, position: Int,modelBooking: BookingAdapterModel)
}
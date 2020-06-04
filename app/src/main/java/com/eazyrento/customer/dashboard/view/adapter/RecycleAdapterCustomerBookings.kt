package com.eazyrento.customer.dashboard.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.R
import com.eazyrento.common.model.modelclass.BookingListItem
import kotlinx.android.synthetic.main.phone_view.view.*
import kotlinx.android.synthetic.main.row_customer_bookings.view.*

class RecycleAdapterCustomerBookings (val bookingDataHolderBinder:BookingDataHolderBinder,val bookingListItem: List<BookingListItem>, val context: Context) : RecyclerView.Adapter<RecycleAdapterCustomerBookings.CardViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {

        val card_view =
            CardViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.row_customer_bookings,
                    parent,
                    false
                )
            )
        return card_view
    }

    override fun getItemCount(): Int {
        return bookingListItem.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

        bookingDataHolderBinder.setDataHolder(holder,position)

        /*//agent details
        holder.tv_booking__name.text=bookingListItem.get(position).agent_detail.full_name
        holder.img_booking__call.contentDescription=bookingListItem.get(position).agent_detail.mobile_number
        //holder?.img_booking__pic.setImageURI("https://eazyrento-qa.s3.amazonaws.com/media/default_profile_pic.png")
        holder?.tv_customer_order_id.text = Constant.ORDER_ID + bookingListItem.get(position).order_id

        // product details
        holder?.tv_customer_date_show.text = bookingListItem.get(position).product_detail.start_date
        holder?.tv_customer_product_quantity.text = bookingListItem.get(position).product_detail.product_name + "-" + bookingListItem.get(position).product_detail.quantity*/
    }

    class CardViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tv_customer_product_quantity = view.tv_pro_quantiity
        val tv_customer_date_show = view.tv_show_date
        val tv_customer_order_id = view.tv_customer_order_id
        val img_booking__pic = view.img_booking__pic
        val img_booking__call = view.phone_view
        val tv_booking__name = view.tv_booking__name
        val tv_booking_type = view.tv_user_type
        val tv_status = view.tv_status
        val btn_accept_booking = view.btn_customer_accept
        val btn_decline_booking = view.btn_customer_decline
    }
}

interface BookingDataHolderBinder{
    fun setDataHolder(holder: RecycleAdapterCustomerBookings.CardViewHolder, position: Int)
}
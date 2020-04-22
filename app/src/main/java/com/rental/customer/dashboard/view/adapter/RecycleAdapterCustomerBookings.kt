package com.rental.customer.dashboard.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rental.R
import com.rental.common.model.modelclass.BookingListItem
import com.rental.common.model.modelclass.Order_listing
import kotlinx.android.synthetic.main.row_customer_bookings.view.*

class RecycleAdapterCustomerBookings (val bookingListItem: MutableList<BookingListItem>, val context: Context) : RecyclerView.Adapter<RecycleAdapterCustomerBookings.CardViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleAdapterCustomerBookings.CardViewHolder {

        val card_view = RecycleAdapterCustomerBookings.CardViewHolder(
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

    override fun onBindViewHolder(holder: RecycleAdapterCustomerBookings.CardViewHolder, position: Int) {
        val order_listing_obj =  bookingListItem.get(position)

        //customer details
        holder?.tv_customer_name.text = order_listing_obj.customer_detail.full_name
        holder?.tv_customer_type.text = order_listing_obj.customer_detail.mobile_number

        // prodect details
        holder?.tv_customer_product_quantity.text = order_listing_obj.product_detail.product_name+
                "-"+order_listing_obj.product_detail.quantity
        holder.tv_customer_date_show.text = order_listing_obj.product_detail.start_date
        holder.tv_customer_order.text = order_listing_obj.order_id
    }

    class CardViewHolder(view: View):RecyclerView.ViewHolder(view){
        val img_customer_pic = view.img_customer_pic
        val tv_customer_name = view.tv_customer_name
        val tv_customer_type = view.tv_customer_type
        val tv_customer_order = view.tv_customer_order
        val tv_customer_product_quantity = view.tv_customer_quantiity
        val tv_customer_date_show = view.tv_show_date

        val btn_customer_accept = view.btn_customer_accept
        val btn_customer_decline = view.btn_customer_decline

    }
}
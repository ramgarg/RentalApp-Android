package com.eazyrento.agent.view.activity

import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.model.modelclass.Booking
import com.eazyrento.common.view.activity.ShowAllBookingActivity
import com.eazyrento.common.view.adapter.DashboardBookingCardAdapter
import com.squareup.picasso.Picasso

class AgentShowAllBookingActivity:ShowAllBookingActivity() {

    override fun setBookingHolder(
        holder: DashboardBookingCardAdapter.CardViewHolder,
        list: List<Booking>,
        position: Int
    ) {

        val order_listing_obj = list.get(position)

        holder?.tv__name.text = order_listing_obj.customer_detail?.full_name
        holder?.tv__type.text = Constant.CUSTOMER

        // prodect details
        holder?.tv__product_quantity.text = order_listing_obj.product_detail?.product_name+ "-"+order_listing_obj.product_detail?.quantity
        holder.tv__date_show.text = order_listing_obj.product_detail?.start_date
        holder.tv__order.text = order_listing_obj.order_id

        Picasso.with(this).load(order_listing_obj.customer_detail?.profile_image).into(holder.img__pic)

        holder.btn__accept.setOnClickListener{
            acceptBooking(order_listing_obj,position, Constant.MERCHNAT_ACCEPTANCE)
        }
        holder.btn__decline.setOnClickListener{
            declineBooking(order_listing_obj,position, Constant.MERCHNAT_ACCEPTANCE)
        }
    }

}
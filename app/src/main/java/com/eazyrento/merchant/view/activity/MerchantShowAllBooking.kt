package com.eazyrento.merchant.view.activity

import com.eazyrento.Constant
import com.eazyrento.common.model.modelclass.Booking
import com.eazyrento.common.view.activity.ShowAllBookingActivity
import com.eazyrento.common.view.adapter.DashboardBookingCardAdapter

class MerchantShowAllBooking:ShowAllBookingActivity() {

    override fun setBookingHolder(
        holder: DashboardBookingCardAdapter.CardViewHolder,
        list: List<Booking>,
        position: Int
    ) {
        val order_listing_obj = list.get(position)

        holder.tv__order.text = order_listing_obj.order_id

        holder.btn__accept.setOnClickListener{
            acceptBooking(order_listing_obj,position,Constant.MERCHNAT_ACCEPTANCE)
        }
        holder.btn__decline.setOnClickListener{
            declineBooking(order_listing_obj,position,Constant.MERCHNAT_ACCEPTANCE)
        }
    }
}
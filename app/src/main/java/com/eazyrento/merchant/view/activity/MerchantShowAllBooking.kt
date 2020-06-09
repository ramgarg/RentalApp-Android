package com.eazyrento.merchant.view.activity

import android.view.View
import com.eazyrento.Constant
import com.eazyrento.common.model.modelclass.Booking
import com.eazyrento.common.model.modelclass.BookingAdapterModel
import com.eazyrento.common.view.activity.ShowAllBookingActivity
import com.eazyrento.common.view.adapter.DashboardBookingCardAdapter
import com.eazyrento.customer.utils.Common

class MerchantShowAllBooking:ShowAllBookingActivity() {

    override fun setBookingHolder(
        holder: DashboardBookingCardAdapter.CardViewHolder,
        list: List<Booking>,
        position: Int,
        modelBooking: BookingAdapterModel
    ) {
        val order_listing_obj = list.get(position)

        val baseUserRoleDetail = order_listing_obj.agent_detail

        baseUserRoleDetail?.let {
            it.userRole = Constant.AGENT
            setBaseDataHolder(position,it,modelBooking,list)
        }

        modelBooking.btn_accept_booking.visibility = View.GONE
        modelBooking.btn_decline_booking.visibility = View.GONE

    }

    /*override fun setBookingHolder(
        holder: DashboardBookingCardAdapter.CardViewHolder,
        list: List<Booking>,
        position: Int
    ) {
        val order_listing_obj = list.get(position)

        holder.tv__order.text = order_listing_obj.order_id

        holder.btn__accept.visibility = View.GONE
        holder.btn__decline.visibility = View.GONE

       *//* holder.btn__accept.setOnClickListener{
            acceptBooking(order_listing_obj,position,Constant.MERCHNAT_ACCEPTANCE)
        }
        holder.btn__decline.setOnClickListener{
            declineBooking(order_listing_obj,position,Constant.MERCHNAT_ACCEPTANCE)
        }*//*

       *//* holder.phone_view.setOnClickListener {
            Common.phoneCallWithNumber(order_listing_obj.agent_detail?.mobile_number,this)
        }*//*
    }*/
}
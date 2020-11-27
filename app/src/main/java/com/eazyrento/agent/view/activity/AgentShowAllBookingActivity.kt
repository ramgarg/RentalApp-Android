package com.eazyrento.agent.view.activity

import android.content.Intent
import android.os.Bundle
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.model.modelclass.Booking
import com.eazyrento.common.model.modelclass.BookingAdapterModel
import com.eazyrento.common.view.activity.ShowAllBookingActivity
import com.eazyrento.common.view.adapter.DashboardBookingCardAdapter
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.toolbar.*

class AgentShowAllBookingActivity:ShowAllBookingActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBackButtonListener()

    }

    override fun setBookingHolder(
        holder: DashboardBookingCardAdapter.CardViewHolder,
        list: List<Booking>,
        position: Int,
        modelBooking:BookingAdapterModel
    ) {
        val order_listing_obj = list.get(position)

        val baseUserRoleDetail = order_listing_obj.customer_detail

        baseUserRoleDetail?.let {
            it.userRole = resources.getString(R.string.customer)
            setBaseDataHolder(position,it,modelBooking,list)
        }

        modelBooking.btn_accept_booking.setOnClickListener{
            acceptBooking(order_listing_obj,position, Constant.AGENT_ACCEPTANCE)
        }
        modelBooking.btn_decline_booking.setOnClickListener{
            declineBooking(order_listing_obj,position, Constant.AGENT_ACCEPTANCE)
        }

    }

    private fun setBackButtonListener(){
        img_back.setOnClickListener {
            moveAgentMainActivity()
        }
    }

    private fun moveAgentMainActivity(){
        MoveToAnotherComponent.moveToActivityWithIntentValue<AgentMainActivity>(this,Constant.VIEW_ALL_BOOKINGS,9)
        finish()
    }

    override fun onBackPressed() {

       // finishCurrentActivityWithResult(Constant.AGENT_VIEW_ALL_REQUEST_CODE, Intent())

        moveAgentMainActivity()

    }
}
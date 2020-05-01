package com.eazyrento.merchant.view.fragment

import android.os.Bundle
import com.eazyrento.Constant
import com.eazyrento.common.model.modelclass.AcceptanceDeclineReqModel
import com.eazyrento.common.model.modelclass.Booking
import com.eazyrento.common.view.adapter.DashboardBookingCardAdapter
import com.eazyrento.common.view.fragment.DashboardBaseFragment

class MerchantDashFragment : DashboardBaseFragment() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        callAPIDashboard(Constant.BOOKING_DASHBOARD_MERCHANT)
    }

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
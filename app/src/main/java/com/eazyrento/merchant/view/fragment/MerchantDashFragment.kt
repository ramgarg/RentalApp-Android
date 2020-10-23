package com.eazyrento.merchant.view.fragment

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.model.modelclass.Booking
import com.eazyrento.common.model.modelclass.BookingAdapterModel
import com.eazyrento.common.model.modelclass.BookingDashboardResModel
import com.eazyrento.common.view.adapter.DashboardBookingCardAdapter
import com.eazyrento.common.view.fragment.DashboardBaseFragment
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.merchant.view.activity.MerchantShowAllBooking
import kotlinx.android.synthetic.main.booking_dashboard_adapter_view.*
import kotlinx.android.synthetic.main.booking_deshboard_bottom_view.*

class MerchantDashFragment : DashboardBaseFragment() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        callAPIDashboard(Constant.BOOKING_DASHBOARD_MERCHANT)

        btn_home_view_all.setOnClickListener {
            MoveToAnotherComponent.openActivityWithParcelableParam<MerchantShowAllBooking, BookingDashboardResModel>(requireContext(),Constant.INTENT_BOOKING_LIST,agentDashboardResponse)
        }

        // rejected count disable for thr merchants...
        lnr_third.visibility = View.GONE
    }

    override fun setBookingHolder(
        holder: DashboardBookingCardAdapter.CardViewHolder,
        list: List<Booking>,
        position: Int,
        modelBooking: BookingAdapterModel
    ) {
        val order_listing_obj = list.get(position)

        val baseUserRoleDetail = order_listing_obj.agent_detail

        baseUserRoleDetail?.let {
            it.userRole = resources.getString(R.string.agent)
            setBaseDataHolder(position,it,modelBooking)
        }

        modelBooking.btn_accept_booking.visibility = View.GONE
        modelBooking.btn_decline_booking.visibility = View.GONE

    }

   /* override fun setBookingHolder(
        holder: DashboardBookingCardAdapter.CardViewHolder,
        list: List<Booking>,
        position: Int
    ) {
       val order_listing_obj = list.get(position)

        holder.tv__order.text = order_listing_obj.order_id
        holder.tv__name.text=order_listing_obj.agent_detail?.full_name
        holder.tv__type.text=order_listing_obj.agent_detail?.mobile_number
        holder.tv__product_quantity.text=order_listing_obj.product_detail?.product_name+"- "+order_listing_obj.product_detail?.quantity

        holder.btn__accept.visibility = View.GONE
        holder.btn__decline.visibility = View.GONE

       *//* holder.btn__accept.setOnClickListener{
            acceptBooking(order_listing_obj,position,Constant.MERCHNAT_ACCEPTANCE)
        }

        holder.btn__decline.setOnClickListener{
            declineBooking(order_listing_obj,position,Constant.MERCHNAT_ACCEPTANCE)
        }*//*

        holder.phone_view.setOnClickListener {
            Common.phoneCallWithNumber(list.get(position).agent_detail?.mobile_number,requireContext())
        }
    }*/


}
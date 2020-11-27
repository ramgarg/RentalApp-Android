package com.eazyrento.agent.view.fragment

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.view.activity.AgentShowAllBookingActivity
import com.eazyrento.common.model.modelclass.Booking
import com.eazyrento.common.model.modelclass.BookingAdapterModel
import com.eazyrento.common.model.modelclass.BookingDashboardResModel
import com.eazyrento.common.view.adapter.DashboardBookingCardAdapter
import com.eazyrento.common.view.fragment.DashboardBaseFragment
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.booking_dashboard_adapter_view.*

class AgentHomeFragment : DashboardBaseFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        callAPIDashboard(Constant.BOOKING_DASHBOARD_AGENT)

        btn_home_view_all.setOnClickListener {

            MoveToAnotherComponent.openActivityWithParcelableParam<AgentShowAllBookingActivity, BookingDashboardResModel>(requireContext(),Constant.INTENT_BOOKING_LIST,agentDashboardResponse)
//            MoveToAnotherComponent.startActivityResultWithParcelable<AgentShowAllBookingActivity, BookingDashboardResModel>(requireActivity(),Constant.INTENT_BOOKING_LIST,agentDashboardResponse,Constant.AGENT_VIEW_ALL_REQUEST_CODE)

        }

    }

    override fun setBookingHolder(
        holder: DashboardBookingCardAdapter.CardViewHolder,
        list: List<Booking>,
        position: Int,
        modelBooking: BookingAdapterModel
    ) {
        val order_listing_obj = list.get(position)

        val baseUserRoleDetail = order_listing_obj.customer_detail

        baseUserRoleDetail?.let {
            it.userRole = resources.getString(R.string.customer)
            setBaseDataHolder(position,it,modelBooking)
        }

        modelBooking.btn_accept_booking.visibility = View.VISIBLE
        modelBooking.btn_decline_booking.visibility = View.VISIBLE

        modelBooking.btn_accept_booking.setOnClickListener {
            acceptBooking(order_listing_obj, position, Constant.AGENT_ACCEPTANCE)
        }
        modelBooking.btn_decline_booking.setOnClickListener {
            declineBooking(order_listing_obj, position, Constant.AGENT_ACCEPTANCE)
        }

    }

}



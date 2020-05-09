package com.eazyrento.agent.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eazyrento.Constant
import com.eazyrento.agent.view.activity.AgentBookingReviewSummeryActivity
import com.eazyrento.common.model.modelclass.BookingListItem
import com.eazyrento.common.view.fragment.MyBookingBaseFragment
import com.eazyrento.customer.dashboard.view.adapter.RecycleAdapterCustomerBookings
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent

class AgentBookingsFragment : MyBookingBaseFragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
           return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        callMyBookingAPI(Constant.BOOKING_LIST_AGENT)
    }

    override fun setDataHolder(
        holder: RecycleAdapterCustomerBookings.CardViewHolder,
        position: Int
    ) {

        //agent details
        holder.tv_booking__name.text=listCustomerBooking.get(position).customer_detail.full_name
        holder.tv_booking_type.text=listCustomerBooking.get(position).customer_detail.mobile_number
        holder.img_booking__call.setOnClickListener {
            Common.phoneCallWithNumber(listCustomerBooking.get(position)?.customer_detail.mobile_number, requireContext())
        }
         //holder?.img_booking__pic.setImageURI("https://eazyrento-qa.s3.amazonaws.com/media/default_profile_pic.png")
        holder?.tv_customer_order_id.text = Constant.ORDER_ID + listCustomerBooking.get(position).order_id

        // product details
        holder?.tv_customer_date_show.text = listCustomerBooking.get(position).product_detail.start_date
        holder?.tv_customer_product_quantity.text = listCustomerBooking.get(position).product_detail.product_name + "-" + listCustomerBooking.get(position).product_detail.quantity

      holder?.itemView.setOnClickListener {

          MoveToAnotherComponent.openActivityWithParcelableParam<AgentBookingReviewSummeryActivity,BookingListItem>(requireContext(),Constant.BOOKING_SUMMERY_KEY,listCustomerBooking.get(position))
      }
    }


}



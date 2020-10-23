package com.eazyrento.agent.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.agent.view.activity.AgentBookingReviewSummeryActivity
import com.eazyrento.common.model.modelclass.BookingAdapterModel
import com.eazyrento.common.model.modelclass.BookingListItem
import com.eazyrento.common.view.fragment.MyBookingBaseFragment
import com.eazyrento.customer.dashboard.view.adapter.RecycleAdapterCustomerBookings
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_customer_bookings.*

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
        modelBooking:BookingAdapterModel,
        position: Int
    ) {
        setBaseDataHolder(holder,position,listCustomerBooking[position].customer_detail.let { it.userRole =resources.getString(R.string.customer)
        it
        },modelBooking)

        modelBooking.btn_accept_booking.visibility=View.GONE
        modelBooking.btn_decline_booking.visibility=View.GONE

        holder.itemView.setOnClickListener {

            if (listCustomerBooking[position].status==Constant.REJECTED)
                return@setOnClickListener

            MoveToAnotherComponent.openActivityWithParcelableParam<AgentBookingReviewSummeryActivity,BookingListItem>(requireContext(),
                Constant.BOOKING_SUMMERY_KEY,listCustomerBooking.get(position))

        }
        /*val obj = listCustomerBooking.get(position)
        //agent details
        holder.tv_booking__name.text=obj.customer_detail.full_name
        holder.tv_booking_type.text=Constant.CUSTOMER
        Picasso.with(requireContext()).load(obj.customer_detail.profile_image).into(holder.img_booking__pic)
        holder.img_booking__call.setOnClickListener {
            Common.phoneCallWithNumber(obj.customer_detail.mobile_number, requireContext())
        }



        holder.tv_customer_order_id.text = Constant.ORDER_ID.plus(obj.order_id)

        // product details
        holder.tv_customer_date_show.text = obj.product_detail.start_date
        holder.tv_customer_product_quantity.text = resources.getString(R.string.quantity).plus(obj.product_detail.quantity)

        holder.btn_accept_booking.visibility=View.GONE
        holder.btn_decline_booking.visibility=View.GONE

        holder.itemView.setOnClickListener {

          MoveToAnotherComponent.openActivityWithParcelableParam<AgentBookingReviewSummeryActivity,BookingListItem>(requireContext(),Constant.BOOKING_SUMMERY_KEY,obj)

        }*/
    }


}



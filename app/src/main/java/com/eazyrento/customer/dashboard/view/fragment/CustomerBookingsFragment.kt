package com.eazyrento.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.view.fragment.MyBookingBaseFragment
import com.eazyrento.customer.dashboard.view.adapter.RecycleAdapterCustomerBookings
import com.eazyrento.customer.utils.Common
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.header.view.*

class CustomerBookingsFragment: MyBookingBaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
//        return inflater.inflate(R.layout.fragment_customer_bookings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        callMyBookingAPI(Constant.BOOKING_LIST_CUSTOMER)


    }

    override fun setDataHolder(
        holder: RecycleAdapterCustomerBookings.CardViewHolder,
        position: Int

    ) {
        setBaseDataHolder(holder,position,listCustomerBooking[position].agent_detail)

        /*  //agent details
         holder.tv_booking__name.text=obj.agent_detail.full_name.capitalize()
         holder.tv_booking_type.text=Constant.AGENT
         Picasso.with(requireContext()).load(obj.agent_detail.profile_image).into(holder.img_booking__pic)
         holder.img_booking__call.setOnClickListener {
             Common.phoneCallWithNumber(obj.agent_detail.mobile_number, requireContext())
         }

         holder.tv_customer_order_id.text = Constant.ORDER_ID + obj.order_id

         // product details
         holder.tv_customer_date_show.text = obj.product_detail.start_date
 //        holder?.tv_customer_product_quantity.text = listCustomerBooking.get(position).product_detail.product_name + "-" + listCustomerBooking.get(position).product_detail.quantity
         holder.tv_customer_product_quantity.text = resources.getString(R.string.quantity)+obj.product_detail.quantity
         holder.tv_status.let {
             it.visibility = View.VISIBLE
             it.text = obj.status
             val bg = when(obj.status){
                 Constant.PENDING-> R.drawable.payment_pending
                 Constant.FAILED -> R.drawable.payment_failed
                 Constant.COMPLETED-> R.drawable.payment_success
                 else-> R.drawable.payment_success
             }
             it.setBackgroundResource(bg)
         }
 */

    }

}